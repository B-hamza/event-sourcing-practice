package com.example.order.commands;

import java.math.BigDecimal;

public class CreateOrderCommand extends OrderCommand {
    
  private final String customerId;
  private final BigDecimal orderTotal;

  public CreateOrderCommand(String customerId, BigDecimal orderTotal) {
    super(CreateOrderCommand.class);
    this.customerId = customerId;
    this.orderTotal = orderTotal;
  }

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  }

  /**
   * @return the orderTotal
   */
  public BigDecimal getOrderTotal() {
    return orderTotal;
  }
}
