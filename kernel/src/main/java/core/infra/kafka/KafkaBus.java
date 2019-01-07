package core.infra.kafka;

import core.eventsourcing.Bus;
import reactor.core.publisher.Mono;

public class KafkaBus<T> implements Bus<T> {

  @Override
  public Mono<Void> publish(String key, T value) {
    return null;
  }

}
