package core.eventsourcing;

import java.util.function.Function;

import reactor.core.publisher.Mono;

public interface Bus<T> {
  Mono<Void> publish(String key, T value);

  Void onEvent(String groupId, Function<T, Mono<Void>> block, int parallelism);
}
