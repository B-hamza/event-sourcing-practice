package com.example.order.stores;

import com.example.order.commands.OrderCommand;
import com.example.order.states.Order;
import com.example.order.states.OrderId;

import core.dao.MongoService;
import core.infra.mongo.MongoStore;

public class OrderStore extends MongoStore<OrderId, Order, Long, OrderCommand, String> {
  
  public OrderStore(MongoService mongoService) {
    super(mongoService, "order");
  }
}
