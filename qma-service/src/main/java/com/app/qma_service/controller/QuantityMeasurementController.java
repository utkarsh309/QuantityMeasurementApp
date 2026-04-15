package com.app.qma_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.qma_service.model.QuantityInputDTO;
import com.app.qma_service.model.QuantityMeasurementDTO;
import com.app.qma_service.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(
            @Valid @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "username", required = false) String username) {

        QuantityMeasurementDTO result = service.compare(input, username);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(
            @Valid @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "username", required = false) String username) {

        QuantityMeasurementDTO result = service.convert(input, username);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(
            @Valid @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "username", required = false) String username) {

        QuantityMeasurementDTO result = service.add(input, username);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(
            @Valid @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "username", required = false) String username) {

        QuantityMeasurementDTO result = service.subtract(input, username);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(
            @Valid @RequestBody QuantityInputDTO input,
            @RequestHeader(value = "username", required = false) String username) {

        QuantityMeasurementDTO result = service.divide(input, username);

        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/history/{operation}")
    public List<QuantityMeasurementDTO> getHistory(
            @PathVariable String operation,
            @RequestHeader("username") String username) {

        return service.getHistoryByOperation(operation.toUpperCase(), username);
    }

}