package core.eventsourcing;

import java.util.List;
import java.util.Optional;

import reactor.core.publisher.Mono;

/**
 * State store
 * 
 * @param <ID_STATE> state id type
 * @param <STATE> state type
 */
public interface Store<ID_STATE extends AggregateId, S extends State<S>, ID_COMMAND, C extends Command<C>, U> {

  Mono<Boolean> save(ID_STATE id, S state, Event<ID_COMMAND, C, ID_STATE, U> event);

  Mono<Optional<S>> get(ID_STATE id);

  Mono<Optional<Aggregate<ID_STATE, S, ID_COMMAND>>> getAggregate(ID_STATE id);

  Mono<Boolean> removeFirstEvent(ID_STATE id);

  Mono<List<S>> getAll();

}
