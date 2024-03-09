package com.example.kafkaproducerrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {
    private Map<String, String> headers;
    private String topic;
    private String key;
    private Object payload;
}
