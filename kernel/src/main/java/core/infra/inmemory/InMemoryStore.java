package core.infra.inmemory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import reactor.core.publisher.Mono;

import com.mongodb.client.MongoCollection;

import org.springframework.boot.autoconfigure.mongo.ReactiveMongoClientFactory;
import org.springframework.data.mongodb.core.ReactiveCollectionCallback;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import core.eventsourcing.Aggregate;
import core.eventsourcing.AggregateId;
import core.eventsourcing.Command;
import core.eventsourcing.Event;
import core.eventsourcing.Store;
import core.eventsourcing.State;

public class InMemoryStore<ID_STATE extends AggregateId, S extends State<S>, ID_COMMAND, C extends Command<C>, U> 
  implements Store<ID_STATE, S, ID_COMMAND, C , U> {
  
  private final List<Proxy> list;

  public InMemoryStore() {
    this.list = new CopyOnWriteArrayList<>();
  }
  
  @Override
  public Mono<Boolean> save(ID_STATE id, S state, Event<ID_COMMAND, C, ID_STATE, U> event) {
    return getProxy(id).map(proxy -> {
      List<Event<ID_COMMAND, C, ID_STATE, U>> events = proxy.map(p -> {
        List<Event<ID_COMMAND, C, ID_STATE, U>> newList = new CopyOnWriteArrayList<>();
        newList.addAll(p.getPendingEvents());
        newList.add(event);
        return newList;
      }).orElse(new CopyOnWriteArrayList<>(Arrays.asList(event)));
      return new Proxy(id, state, event.getIdCommand(), events);
    }).map(proxy -> {
      return this.list.add(proxy);
    });
  }

  @Override
  public Mono<Optional<S>> get(ID_STATE id) {
    return getProxy(id).map(proxy -> proxy.map(p -> p.state));
  }

  @Override
  public Mono<Optional<Aggregate<ID_STATE, S, ID_COMMAND>>> getAggregate(ID_STATE id) {
    return getProxy(id).map(proxy -> proxy.map(p -> 
      new Aggregate<>(p.idState, p.version, p.state)
    ));
  }

  @Override
  public Mono<Boolean> removeFirstEvent(ID_STATE id) {
    this.list.replaceAll(proxy -> {
      if(proxy.getIdState().equals(id) && !proxy.getPendingEvents().isEmpty()) {
        proxy.getPendingEvents().remove(proxy.getPendingEvents().size() - 1);
        return proxy;
      }
      return proxy;
    });
    return Mono.just(true);
  }

  private final Mono<Optional<Proxy>> getProxy(ID_STATE id) {
    return Mono.just(list.stream().filter(proxy -> proxy.getIdState().equals(id))
      .findFirst());
  }

  /**
   * @return the list
   */
  public Mono<List<S>> getAll() {
    return Mono.just(Collections.unmodifiableList(
        list.stream().map(proxy -> proxy.getState())
          .collect(Collectors.toList())
      ));
  }

  class Proxy {
    private final ID_STATE idState;
    private final S state;
    private final ID_COMMAND version;
    private final List<Event<ID_COMMAND, C, ID_STATE, U>> pendingEvents;

    public Proxy(ID_STATE idState, S state, ID_COMMAND version, List<Event<ID_COMMAND, C, ID_STATE, U>> pendingEvents) {
      this.idState = idState;
      this.state = state;
      this.version = version;
      this.pendingEvents = pendingEvents;
    }

    /**
     * @return the idState
     */
    public ID_STATE getIdState() {
      return idState;
    }

    /**
     * @return the state
     */
    public S getState() {
      return state;
    }

    /**
     * @return the version
     */
    public ID_COMMAND getVersion() {
      return version;
    }

    /**
     * @return the pendingEvents
     */
    public List<Event<ID_COMMAND, C, ID_STATE, U>> getPendingEvents() {
      return pendingEvents;
    }
  }

}
