package com.example.order.states;

import core.eventsourcing.State;

public class Order extends State<Order> {

  public Order() {
    super(Order.class);
  }

  public Order(Class<? extends Order> stateType) {
    super(stateType);

  }

}
