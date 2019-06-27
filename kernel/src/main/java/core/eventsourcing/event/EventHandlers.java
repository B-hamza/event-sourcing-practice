package core.eventsourcing.event;

import core.eventsourcing.aggregate.State;

import java.util.List;

public class EventHandlers<EVENT extends Event<?>, STATE extends State<STATE>> {

    private List<EventHandler<EVENT, STATE>> handlers;

    public EventHandlers(List<EventHandler<EVENT, STATE>> handlers) {
        this.handlers = handlers;
    }

    public STATE apply(State<STATE> state, EVENT event) {
        return handlers.stream()
                       .filter(h ->
                                   h.getEventClass().getName().equals(event.getClass().getName())
                       )
                       .findFirst()
                       .map(handler -> handler.apply(state, event))
                       .orElseThrow(() -> new RuntimeException("TODO EXCEPTION"));
    }
}
