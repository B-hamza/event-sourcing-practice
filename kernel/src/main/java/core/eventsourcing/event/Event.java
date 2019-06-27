package core.eventsourcing.event;

import core.eventsourcing.aggregate.AggregateId;
import core.eventsourcing.aggregate.AggregateVersion;

import java.time.ZonedDateTime;

/**
 * An event is a command which has been validated through event sourcing. It is
 * stored in the event log (journal).
 *
 * @param <U> User Context type
 */
@SuppressWarnings("rawtypes")
public class Event<U> {

  private final AggregateId idAggregate;
  private final AggregateVersion idCommand;
  private final ZonedDateTime time;
  private final U context;

  public Event(AggregateId idAggregate, AggregateVersion idCommand, ZonedDateTime time, U context) {
    this.idAggregate = idAggregate;
    this.idCommand = idCommand;
    this.time = time;
    this.context = context;
  }

  /**
   * @return the idAggregate
   */
  public AggregateId getIdAggregate() {
    return idAggregate;
  }

  /**
   * @return the idCommand
   */
  public AggregateVersion getIdCommand() {
    return idCommand;
  }

  /**
   * @return the time
   */
  public ZonedDateTime getTime() {
    return time;
  }

  /**
   * @return the context
   */
  public U getContext() {
    return context;
  }

}
