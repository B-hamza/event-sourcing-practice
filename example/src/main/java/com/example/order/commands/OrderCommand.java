package com.example.order.commands;

import core.eventsourcing.Command;

public class OrderCommand extends Command<OrderCommand> {

  public OrderCommand(Class<? extends OrderCommand> commandType) {
    super(commandType);
  }
}
