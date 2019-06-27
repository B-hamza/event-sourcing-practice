package com.example.order.handlers;

import java.math.BigDecimal;

import reactor.test.StepVerifier;
import org.junit.jupiter.api.Test;

import core.dao.MongoService;

import com.example.order.commands.CreateOrderCommand;
import com.example.order.states.OrderId;
import com.example.order.commands.ApprovedOrderCommand;
import com.example.order.states.ApprovedOrder;
import com.example.order.states.PendingOrder;

public class OrderEventSourcedTest {

  private final OrderEventSourced eventSourced = new OrderEventSourced(new MongoService());
  private final OrderId orderId = new OrderId();

  @Test
  void shouldCreateAndStoreOrders() {
    // create a command
    CreateOrderCommand command = new CreateOrderCommand("Eric", new BigDecimal(100));

    StepVerifier.create(eventSourced.create(orderId, command, "Eric"))
      .expectNextMatches(aggregate -> aggregate.getState().getType().equals(PendingOrder.class))
      .verifyComplete();

    StepVerifier.create(eventSourced.getStore().getAll())
      .expectNextMatches(list -> !list.isEmpty() && list.size() == 1)
      .verifyComplete();

    // should receive other commands
    ApprovedOrderCommand approvedOrderCommand = new ApprovedOrderCommand();

    StepVerifier.create(eventSourced.create(orderId, approvedOrderCommand, "Thomas"))
      .expectNextMatches(aggregate -> aggregate.getState().getType().equals(ApprovedOrder.class))
      .verifyComplete();

    StepVerifier.create(eventSourced.getStore().getAll())
      .expectNextMatches(list -> !list.isEmpty() && list.size() == 2)
      .verifyComplete();
  }

}
