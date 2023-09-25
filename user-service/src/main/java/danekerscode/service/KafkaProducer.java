package danekerscode.service;

public interface KafkaProducer {
    <T> void produce(String topic, T t);
}
