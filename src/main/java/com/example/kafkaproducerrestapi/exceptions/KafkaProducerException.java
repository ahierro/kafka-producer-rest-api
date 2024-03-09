package com.example.kafkaproducerrestapi.exceptions;

public class KafkaProducerException extends RuntimeException{
    public KafkaProducerException(Throwable cause) {
        super(cause);
    }
}
