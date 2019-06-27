package core.eventsourcing.event;

import core.eventsourcing.aggregate.State;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@SuppressWarnings("rawtypes")
public class EventHandlerBuilder<EVENT extends Event<?>, STATE extends State<STATE>> {
    private List<EventHandler<EVENT, STATE>> eventHandlers;

    private EventHandlerBuilder() {
        this.eventHandlers = new ArrayList<>();
    }

    public static <U extends Event<?>, T extends State<T>> EventHandlerBuilder<U, T> create() {
        return new EventHandlerBuilder<>();
    }

    public <E extends EVENT> EventHandlerBuilder<EVENT, STATE> onEvent(
        Class<E> eventClass,
        BiFunction<State<STATE>, E, STATE> f
    ) {
        @SuppressWarnings("unchecked")
        EventHandler<EVENT, STATE> handler = (EventHandler<EVENT, STATE>)new EventHandler<>(eventClass, f);
        this.eventHandlers.add(handler);
        return this;
    }

    public EventHandlers<EVENT, STATE> build() {
        return new EventHandlers<>(eventHandlers);
    }

}