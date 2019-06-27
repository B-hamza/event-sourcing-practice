package core.infra.mongo;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import core.dao.MongoService;
import core.eventsourcing.aggregate.Aggregate;
import core.eventsourcing.aggregate.AggregateId;
import core.eventsourcing.command.Command;
import core.eventsourcing.event.Event;
import core.eventsourcing.store.Store;
import core.eventsourcing.aggregate.State;

public class MongoStore<ID_STATE extends AggregateId, S extends State<S>, ID_COMMAND, C extends Command<C>, U> 
  implements Store<ID_STATE, S, ID_COMMAND, C , U> {
  
  private final String collectionName;

  public MongoStore(MongoService mongoService, String collectionName) {
    this.collectionName = collectionName;
  }

  @Override
  public Mono<Boolean> save(ID_STATE id, S state, Event<ID_COMMAND, C, ID_STATE, U> event) {
    return null;
  }

  @Override
  public Mono<Optional<S>> get(ID_STATE id) {
    return null;
  }

  @Override
  public Mono<Optional<Aggregate<ID_STATE, S, ID_COMMAND>>> getAggregate(ID_STATE id) {
    return null;
  }

  @Override
  public Mono<Boolean> removeFirstEvent(ID_STATE id) {
    return null;
  }

  public Mono<List<S>> getAll() {
    return null;
  }

}
