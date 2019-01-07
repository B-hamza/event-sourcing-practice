package core.eventsourcing;

import java.util.function.BiFunction;

@SuppressWarnings("rawtypes")
public class Handler<S extends State, C extends Command, U extends State> {

  private final Class<S> stateClass;
  private final Class<C> commandClass;
  private final BiFunction<S, C, U> handler;

  public Handler(
    Class<S> stateClass,
    Class<C> commandClass,
    BiFunction<S, C, U> handler
  ) {
    this.stateClass = stateClass;
    this.commandClass = commandClass;
    this.handler = handler;
  }

  public Class<S> getStateClass() {
    return stateClass;
  }

  public Class<C> getCommandClass() {
    return commandClass;
  }

  public U apply(S state, C command) {
    return handler.apply(state, command);
  }

}
  

