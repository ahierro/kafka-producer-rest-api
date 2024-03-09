package com.example.kafkaproducerrestapi.controllers;

import com.example.kafkaproducerrestapi.ProducerService;
import com.example.kafkaproducerrestapi.dto.RecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Producer")
@RequestMapping("/record")
@AllArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @Operation(summary = "Create",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Record Created"),
                    @ApiResponse(responseCode = "500", description = "Internal Error")})
    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody RecordDTO recordDTO) {
        producerService.create(recordDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
