package com.example.kafkaproducerrestapi;

import com.example.kafkaproducerrestapi.dto.RecordDTO;
import com.example.kafkaproducerrestapi.exceptions.KafkaProducerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaProducer;

    public void create(RecordDTO recordDTO) {
        try {
            MessageHeaderAccessor accessor = new MessageHeaderAccessor();
            accessor.copyHeaders(recordDTO.getHeaders());
            kafkaProducer.send(MessageBuilder
                    .withPayload(recordDTO.getPayload())
                    .setHeaders(accessor)
                    .setHeader(KafkaHeaders.KEY, recordDTO.getKey())
                    .setHeader(KafkaHeaders.TOPIC, recordDTO.getTopic())
                    .build()).get();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new KafkaProducerException(e);
        }
    }
}
