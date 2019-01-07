package core.eventsourcing;

import reactor.core.publisher.Mono;

public interface EventStore<ID_COMMAND, C extends Command<C>, ID_STATE extends AggregateId, U> {

  Mono<Void> save(Event<ID_COMMAND, C, ID_STATE, U> event);

}
