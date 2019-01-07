package core.eventsourcing;

import java.time.ZonedDateTime;

/**
 * An event is a command which has been validated through event sourcing. It is
 * stored in the event log (journal).
 * 
 * @param <ID_COMMAND> command id type
 * @param <COMMAND> command type
 * @param <ID_STATE> state id type
 * @param <U> User Context type
 */
public class Event<ID_COMMAND, C extends Command<C>, ID_STATE extends AggregateId, U> {

  private final ID_STATE idAggregate;
  private final ID_COMMAND idCommand;
  private final C command;
  private final ZonedDateTime time;
  private final U context;

  public Event(ID_STATE idAggregate, ID_COMMAND idCommand, C command, ZonedDateTime time, U context) {
    this.idAggregate = idAggregate;
    this.idCommand = idCommand;
    this.command = command;
    this.time = time;
    this.context = context;
  }

  /**
   * @return the idAggregate
   */
  public ID_STATE getIdAggregate() {
    return idAggregate;
  }

  /**
   * @return the idCommand
   */
  public ID_COMMAND getIdCommand() {
    return idCommand;
  }

  /**
   * @return the command
   */
  public C getCommand() {
    return command;
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
