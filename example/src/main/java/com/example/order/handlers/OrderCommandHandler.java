package com.example.order.handlers;

import core.eventsourcing.CommandHandlerBuilder;
import core.eventsourcing.Handlers;

import com.example.order.commands.ApprovedOrderCommand;
import com.example.order.commands.CreateOrderCommand;
import com.example.order.commands.OrderCommand;

import com.example.order.states.ApprovedOrder;
import com.example.order.states.Order;
import com.example.order.states.PendingOrder;

public class OrderCommandHandler {
    
  private final Handlers<Order, OrderCommand> handlers;

  public OrderCommandHandler() {
    this.handlers = CommandHandlerBuilder.<Order, OrderCommand>create()
      .onCommand(CreateOrderCommand.class, Order.class, this::createOrder)
      .onCommand(ApprovedOrderCommand.class, PendingOrder.class, this::approvedCommand)
      .build();
  }

  public PendingOrder createOrder(Order emptyOrder, CreateOrderCommand command) {
    return new PendingOrder(command.getCustomerId());
  }

  public ApprovedOrder approvedCommand(PendingOrder pendingOrder, ApprovedOrderCommand command) {
    return new ApprovedOrder(pendingOrder.getCustomerId());
  }

  public Handlers<Order, OrderCommand> getHandlers() {
    return handlers;
  }
}
