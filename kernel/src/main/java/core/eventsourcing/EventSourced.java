package core.eventsourcing;

import core.eventsourcing.aggregate.Aggregate;
import core.eventsourcing.aggregate.AggregateId;
import core.eventsourcing.aggregate.AggregateVersion;
import core.eventsourcing.aggregate.State;
import core.eventsourcing.command.Command;
import core.eventsourcing.command.Handlers;
import core.eventsourcing.event.Event;
import core.eventsourcing.store.Store;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

public abstract class EventSourced<S extends State<S>, C extends Command<C>, U> {

    private final Handlers<S, C> handlers;

    private final S empty;

    private final Store<S, U> store;

    private final Bus<Event<U>> eventPublisher;

    public EventSourced(Handlers<S, C> handlers, S empty, Store<S, U> store,
                        Bus<Event<U>> eventPublisher) {
        this.handlers = handlers;
        this.empty = empty;
        this.store = store;
        this.eventPublisher = eventPublisher;
    }

    protected abstract AggregateVersion generateCommandeId();

    public Mono<Aggregate<S>> create(AggregateId idState, C command, U user) {
        return upsert(idState, command, user);
    }

    public Mono<Aggregate<S>> upsert(AggregateId idState, C command, U user) {
        return store.get(idState).flatMap(maybeState -> {
            // TODO : don't use get ! must manage errors and return Mono.error instead
            final S state = handlers.apply(maybeState.orElse(empty), command).get();
            return saveAndPublish(state, idState, user);
        });
    }

    private Mono<Aggregate<S>> saveAndPublish(S state, AggregateId idState, U user) {
        final AggregateVersion version = generateCommandeId();
        final Event<U> event = new Event<>(idState, version, ZonedDateTime.now(), user);
        // TODO : handle retry
        return store.save(idState, state, event)
                    // .then(eventPublisher.publish(idState.stringify(), event))
                    // .flatMap(m -> store.removeFirstEvent(idState))
                    .map(m -> new Aggregate<>(idState, version, null));
    }

    public Handlers<S, C> getHandlers() {
        return handlers;
    }

    public S getEmpty() {
        return empty;
    }

    public Store<S, U> getStore() {
        return store;
    }

    public Bus<Event<U>> getEventPublisher() {
        return eventPublisher;
    }
}
