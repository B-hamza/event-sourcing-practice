package core.eventsourcing.command;

import core.eventsourcing.TypeTag;

@SuppressWarnings("rawtypes")
public class Command<C extends Command> extends TypeTag<C> {

  public Command(Class<? extends C> commandType) {
    super(commandType);
  }

  /**
   * @return the commandType
   */
  public String getCommandType() {
    return this.getTypeName();
  }
}
