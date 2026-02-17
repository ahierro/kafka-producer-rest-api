package com.example.kafkaproducerrestapi;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

    @Value("${kafka.streams.input-topic:default-topic}")
    private String inputTopic;

    @Value("${kafka.streams.output-topic:default-topic-enrich}")
    private String outputTopic;

    @Bean
    public KStream<String, String> enrichStream(StreamsBuilder streamsBuilder) {
        KStream<String, String> stream = streamsBuilder
                .stream(inputTopic, Consumed.with(Serdes.String(), Serdes.String()));

        stream.mapValues(value -> value == null ? "hello" : value + "hello")
                .to(outputTopic, Produced.with(Serdes.String(), Serdes.String()));

        return stream;
    }
}
