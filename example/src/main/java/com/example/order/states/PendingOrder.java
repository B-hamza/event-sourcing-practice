package com.example.order.states;

public class PendingOrder extends Order {

  private final String customerId;

  public PendingOrder(String customerId) {
    super(PendingOrder.class);
    this.customerId = customerId;
  }

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  }
}
