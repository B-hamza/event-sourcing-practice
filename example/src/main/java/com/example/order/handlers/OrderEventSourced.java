package com.example.order.handlers;

import java.util.concurrent.atomic.AtomicLong;

import core.dao.MongoService;
import core.eventsourcing.EventSourced;

import com.example.order.commands.OrderCommand;
import com.example.order.states.Order;
import com.example.order.states.OrderId;
import com.example.order.stores.InMemoryOrderStore;

public class OrderEventSourced extends EventSourced<OrderId, Order, Long, OrderCommand, String> {
    
  private final MongoService mongoService;
  private final AtomicLong longId;

  public OrderEventSourced(MongoService mongoService) {
    super(
      new OrderCommandHandler().getHandlers(),
      new Order(),
      new InMemoryOrderStore(),
      null
    );
    this.mongoService = mongoService;
    this.longId = new AtomicLong();
  }

  @Override
  public Long generateCommandeId() {
    return longId.getAndIncrement();
  }

}
