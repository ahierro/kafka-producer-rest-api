package com.example.kafkaproducerrestapi.controllers;

import com.example.kafkaproducerrestapi.exceptions.KafkaProducerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(KafkaProducerException.class)
    public ProblemDetail handleBadRequest(KafkaProducerException ex) {
        log.error(ex.getMessage(),ex);
        return ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
