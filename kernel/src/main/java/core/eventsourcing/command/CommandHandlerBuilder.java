package core.eventsourcing.command;

import core.eventsourcing.aggregate.State;

import java.util.List;
import java.util.function.BiFunction;
import java.util.ArrayList;

@SuppressWarnings("rawtypes")
public class CommandHandlerBuilder<S extends State, C extends Command> {
  private List<Handler<S, C, ? extends S>> handlers;

  public CommandHandlerBuilder() {
    this.handlers = new ArrayList<>();
  }

  public static <T extends State, U extends Command> CommandHandlerBuilder<T, U> create() {
    return new CommandHandlerBuilder<T, U>();
  }

  public <T extends S, U extends C, V extends S> CommandHandlerBuilder<S, C> onCommand(
    Class<U> commandClass,
    Class<T> stateClass,
    BiFunction<T, U, V> handler
  ) {
    @SuppressWarnings("unchecked")
    Handler<S, C, V> handlers = (Handler<S, C, V>)new Handler<>(stateClass, commandClass, handler);
    this.handlers.add(handlers);
    return this;
  }

  public Handlers<S, C> build() {
    return new Handlers<>(handlers);
  }

}
