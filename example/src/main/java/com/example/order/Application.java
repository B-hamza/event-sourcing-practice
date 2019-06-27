package com.example.order;

import java.math.BigDecimal;

import com.example.order.commands.CreateOrderCommand;
import com.example.order.handlers.OrderEventSourced;
import com.example.order.states.OrderId;

public class Application {

  static OrderEventSourced eventSourced = new OrderEventSourced(null);

  public static void main(String[] args) {
    CreateOrderCommand command = new CreateOrderCommand("Eric", new BigDecimal(100));
    eventSourced.create(new OrderId(), command, "Eric");
  }
}
