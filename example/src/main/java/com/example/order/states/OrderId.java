package com.example.order.states;

import java.util.UUID;

import core.eventsourcing.AggregateId;

public class OrderId implements AggregateId {
  
  private final UUID id;

  public OrderId() {
    this.id = UUID.randomUUID();
  }

  public OrderId(UUID id) {
    this.id = id;
  }

  /**
   * @return the id
   */
  public UUID getId() {
    return id;
  }

  @Override
  public String stringify() {
    return id.toString();
  }  

}
