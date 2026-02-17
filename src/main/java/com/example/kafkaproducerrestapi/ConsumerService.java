package com.example.kafkaproducerrestapi;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
public class ConsumerService {

    @KafkaListener(
            topics = "#{@consumerTopics}",
            id = "consumer-logger"
    )
    public void consume(
            @Payload String payload,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            ConsumerRecord<String, String> record
    ) {
        String headersStr = record.headers() != null
                ? java.util.stream.StreamSupport.stream(record.headers().spliterator(), false)
                .map(h -> h.key() + "=" + new String(h.value()))
                .collect(Collectors.joining(", "))
                : "";

        log.info("Consumed message | topic={} | partition={} | offset={} | key={} | headers=[{}] | payload={}",
                topic, partition, offset, key, headersStr, payload);
    }
}
