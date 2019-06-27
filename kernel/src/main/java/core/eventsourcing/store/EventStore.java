package core.eventsourcing.store;

import core.eventsourcing.event.Event;
import reactor.core.publisher.Mono;

public interface EventStore<U> {

  Mono<Void> save(Event<U> event);

}
