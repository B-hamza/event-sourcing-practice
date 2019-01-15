package com.example.customer.commands;

import java.math.BigDecimal;
public class CreateCustomerCommand extends CustomerCommand {
  private final String name;
  private final BigDecimal creditLimit;
  public CreateCustomerCommand(String name, BigDecimal creditLimit) {
    super(CreateCustomerCommand.class);
    this.name = name;
    this.creditLimit = creditLimit;
  }

  /**
   * @return the creditLimit
   */
  public BigDecimal getCreditLimit() {
    return creditLimit;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }
}
