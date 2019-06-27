package core.eventsourcing.event;

import core.eventsourcing.aggregate.State;

import java.util.function.BiFunction;

public class EventHandler<EVENT extends Event, STATE extends State<STATE>> {

    private final Class<EVENT> eventClass;
    private final BiFunction<State<STATE>, EVENT, STATE> handler;

    public EventHandler(
        Class<EVENT> eventClass,
        BiFunction<State<STATE>, EVENT, STATE> handler) {
        this.eventClass = eventClass;
        this.handler = handler;
    }

    public Class<EVENT> getEventClass() {
        return eventClass;
    }

    public STATE apply(State<STATE> state, EVENT event) {
        return handler.apply(state, event);
    }

}
