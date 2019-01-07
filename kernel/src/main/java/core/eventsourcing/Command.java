package core.eventsourcing;

@SuppressWarnings("rawtypes")
public class Command<C extends Command> {
  private final Class<? extends C> commandType;

  public Command(Class<? extends C> commandType) {
    this.commandType = commandType;
  }

  /**
   * @return the commandType
   */
  public String getCommandType() {
    return commandType.getName();
  }
}
