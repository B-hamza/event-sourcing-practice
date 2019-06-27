package core.eventsourcing.aggregate;

import core.eventsourcing.event.Event;
import core.eventsourcing.event.EventHandlers;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * The aggregate is the (unique) target of an event in the event sourcing
 * driven design architecture. It identifies one state which will be updated by
 * event.
 *
 * @param <S>      state type
 */
public class Aggregate<S extends State<S>> {

  private final AggregateId id;
  private final AggregateVersion version;
  private final List<Event<?>> events;

  public Aggregate(AggregateId id, AggregateVersion version, List<Event<?>> events) {
    this.id = id;
    this.version = version;
    this.events = events;

  }

  public Aggregate(AggregateId id, AggregateVersion version) {
    this.id = id;
    this.version = version;
    this.events = Collections.emptyList();
  }

  public AggregateId getId() {
    return id;
  }

  public AggregateVersion getVersion() {
    return version;
  }

  public Function<EventHandlers<Event<?>, S>, S> computeState() {
    return (eventHandlers) -> {
      State<S> newState = events.stream().reduce(
          State.empty(),
          eventHandlers::apply,
          (state1, state2) -> state2
      );
      return (S) newState;
    };
  }

  public List<Event<?>> getEvents() {
    return events;
  }

}
