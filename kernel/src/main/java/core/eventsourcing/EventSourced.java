package core.eventsourcing;

import java.time.ZonedDateTime;

import reactor.core.publisher.Mono;

public abstract class  EventSourced<ID_STATE extends AggregateId, S extends State<S>, ID_COMMAND, C extends Command<C>, U> {

  private final Handlers<S, C> handlers;

  private final S empty;

  private final Store<ID_STATE, S, ID_COMMAND, C, U> store;

  private final Bus<Event<ID_COMMAND, C, ID_STATE, U>> eventPublisher;

  public EventSourced(
    Handlers<S, C> handlers,
    S empty,
    Store<ID_STATE, S, ID_COMMAND, C, U> store,
    Bus<Event<ID_COMMAND, C, ID_STATE, U>> eventPublisher
  ) {
    this.handlers = handlers;
    this.empty = empty;
    this.store = store;
    this.eventPublisher = eventPublisher;
  }

  protected abstract ID_COMMAND generateCommandeId();

  public Mono<Aggregate<ID_STATE, S, ID_COMMAND>>  create(ID_STATE idState, C command, U user) {
    return upsert(idState, command, user);
  }

  public Mono<Aggregate<ID_STATE, S, ID_COMMAND>>  upsert(ID_STATE idState, C command, U user) {
    return store.get(idState)
      .flatMap(maybeState -> {
        // TODO : don't use get ! must manage errors and return Mono.error instead
        final S state = handlers.apply(maybeState.orElse(empty), command).get();
        return saveAndPublish(state, idState, command, user);
      }
    );
  }

  private Mono<Aggregate<ID_STATE, S, ID_COMMAND>> saveAndPublish(S state, ID_STATE idState, C command, U user) {
    final ID_COMMAND version = generateCommandeId();
    final Event<ID_COMMAND, C, ID_STATE, U> event = new Event<>(idState, version, command, ZonedDateTime.now(), user);
    // TODO : handle retry
    return store.save(idState, state, event)
      //.then(eventPublisher().publish(idState.stringify(), event))
      .flatMap(m -> store.removeFirstEvent(idState))
      .map(m -> new Aggregate<>(idState, version, state));
  }

  public Handlers<S, C> getHandlers() {
    return handlers;
  }

  public S getEmpty() {
    return empty;
  }

  public Store<ID_STATE, S, ID_COMMAND, C, U> getStore() {
    return store;
  }

  public Bus<Event<ID_COMMAND, C, ID_STATE, U>> getEventPublisher() {
    return eventPublisher;
  }
}
