package com.example.communs.events;

import java.time.ZonedDateTime;
import java.util.UUID;

import core.eventsourcing.Event;

import com.example.order.commands.CreateOrderCommand;
import com.example.order.states.OrderId;

public class OrderCreated extends Event<UUID, CreateOrderCommand, OrderId, String> {
  public OrderCreated(OrderId stateId, UUID commandId, CreateOrderCommand command, ZonedDateTime time, String context) {
    super(stateId, commandId, command, time, context);
  }
}
