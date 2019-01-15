package com.example.order.states;

public class ApprovedOrder extends Order {

  private final String customerId;

  public ApprovedOrder(String customerId) {
    super(ApprovedOrder.class);
    this.customerId = customerId;
  }

  /**
   * @return the customerId
   */
  public String getCustomerId() {
    return customerId;
  } 

}
