package com.example.kafkaproducerrestapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public String[] consumerTopics(@Value("${kafka.consumer.topics}") String topics) {
        return Arrays.stream(topics.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}
