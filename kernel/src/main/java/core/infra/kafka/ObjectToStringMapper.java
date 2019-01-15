package core.infra.kafka;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

@SuppressWarnings("rawtypes")
class ObjectToStringMapper<T> implements Serializer<T>, Deserializer<T> {
    
  private final ObjectMapper objectMapper;
  private final Class<T> type;
  private final StringDeserializer stringDeserializer = new StringDeserializer();
  private final StringSerializer stringSerializer = new StringSerializer();
  public ObjectToStringMapper(ObjectMapper objectMapper, Class<T> type) {
    this.objectMapper = objectMapper;
    this.type = type;
  }

  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    stringDeserializer.configure(configs, isKey);
    stringSerializer.configure(configs, isKey);
  }

  @Override
  public byte[] serialize(String topic, T data) {
    try {
      return stringSerializer.serialize(topic, objectMapper.writeValueAsString(data));
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public T deserialize(String topic, byte[] data) {
    try {
      String dataString = stringDeserializer.deserialize(topic, data);
      return objectMapper.readValue(dataString, type);
    } catch(IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void close() {
    stringDeserializer.close();
    stringSerializer.close();
  }

}
