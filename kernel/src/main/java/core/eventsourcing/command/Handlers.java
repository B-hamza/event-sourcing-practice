package core.eventsourcing.command;

import core.eventsourcing.aggregate.State;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("rawtypes")
public class Handlers<S extends State, C extends Command> {

  private List<Handler<S, C, ? extends S>> handlers;

  public Handlers(List<Handler<S, C, ? extends S>> handlers) {
    this.handlers = handlers;
  }

  public Optional<S> apply(S state, C command) {
    return handlers.stream().filter(h -> 
      h.getStateClass().getName().equals(state.getStateType()) &&
      h.getCommandClass().getName().equals(command.getCommandType())
    )
    .findFirst()
    .map(handler -> handler.apply(state, command));
  }

}
