package com.example.customer.commands;

import core.eventsourcing.Command;

public class CustomerCommand extends Command<CustomerCommand> {
  public CustomerCommand(Class<? extends CustomerCommand> type) {
    super(type);
  }
}
