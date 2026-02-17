package com.example.kafkaproducerrestapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "default-topic")
    private String topic;
    private String key;
    private Object payload;
}
