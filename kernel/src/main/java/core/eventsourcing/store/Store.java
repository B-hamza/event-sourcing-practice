package core.eventsourcing.store;

import java.util.List;
import java.util.Optional;

import core.eventsourcing.aggregate.Aggregate;
import core.eventsourcing.aggregate.AggregateId;
import core.eventsourcing.aggregate.State;
import core.eventsourcing.event.Event;
import reactor.core.publisher.Mono;

/**
 * State store
 *
 * @param <S> state type
 */
public interface Store<S extends State<S>, U> {

  Mono<Boolean> save(AggregateId id, S state, Event<U> event);

  Mono<Optional<S>> get(AggregateId id);

  Mono<Optional<Aggregate<S>>> getAggregate(AggregateId id);

  Mono<Boolean> removeFirstEvent(AggregateId id);

  Mono<List<S>> getAll();

}
