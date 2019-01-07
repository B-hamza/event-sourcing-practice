package com.example.order.stores;

import com.example.order.commands.OrderCommand;
import com.example.order.states.Order;
import com.example.order.states.OrderId;

import core.infra.inmemory.InMemoryStore;

public class InMemoryOrderStore extends InMemoryStore<OrderId, Order, Long, OrderCommand, String> {
  
  public InMemoryOrderStore() {
    super();
  }
}
