package core.infra.mongo;

import reactor.core.publisher.Mono;

import core.eventsourcing.AggregateId;
import core.eventsourcing.Command;
import core.eventsourcing.Event;
import core.eventsourcing.EventStore;
public class MongoEventStore<ID_COMMAND, C extends Command<C>, ID_STATE extends AggregateId, U> 
  implements EventStore<ID_COMMAND, C, ID_STATE, U> {
  
  @Override
  public Mono<Void> save(Event<ID_COMMAND, C, ID_STATE, U> event) {
    return null;
  }

}
