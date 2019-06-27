package core.infra.kafka;

import java.util.function.Function;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.eventsourcing.Bus;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

public class KafkaBus<T> implements Bus<T> {

  private final SenderOptions<String, String> senderOptions;
  private final ReceiverOptions<Integer, String> revceiverOptions;

  public KafkaBus(ObjectMapper mapper) {
    this.senderOptions = null;
    this.revceiverOptions = null;
  }

  public KafkaBus(
    SenderOptions<String, String> senderOptions,
    ReceiverOptions<Integer, String> revceiverOptions
  ) {
    this.senderOptions = senderOptions;
    this.revceiverOptions = revceiverOptions;
  }

  @Override
  public Mono<Void> publish(String key, T value) {
    return null;
  }


  @Override
  public Void onEvent(String groupId, Function<T, Mono<Void>> block, int parallelism) {
    return null;
  }

  

}
