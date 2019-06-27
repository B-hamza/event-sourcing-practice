package com.example.customer.commands;

import java.math.BigDecimal;

import com.example.communs.events.OrderCreated;

public class ReserveCreditCommand extends CustomerCommand {

  private final BigDecimal orderTotal;
  private final String orderId;
  public ReserveCreditCommand(BigDecimal orderTotal, String orderId) {
    super(ReserveCreditCommand.class);
    this.orderTotal = orderTotal;
    this.orderId = orderId;
  }
  public CustomerCommand from(OrderCreated event) {
    return new ReserveCreditCommand(event.getCommand().getOrderTotal(), event.getIdAggregate().stringify());
  }

  /**
   * @return the orderTotal
   */
  public BigDecimal getOrderTotal() {
    return orderTotal;
  }

  /**
   * @return the orderId
   */
  public String getOrderId() {
    return orderId;
  }
}
