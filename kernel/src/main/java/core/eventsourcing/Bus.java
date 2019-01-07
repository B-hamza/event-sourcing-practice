package core.eventsourcing;

import reactor.core.publisher.Mono;

public interface Bus<T> {
  Mono<Void> publish(String key, T value);
}
