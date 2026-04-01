package com.app.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;

/**
 * REST Controller exposing APIs for quantity measurement operations.
 */
@RestController
@RequestMapping("/api/v1/quantities")
@CrossOrigin(origins = "*")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    /**
     * Compare two quantities
     */
    @PostMapping("/compare")
    public ResponseEntity<QuantityMeasurementDTO> compare(
            @Valid @RequestBody QuantityInputDTO input) {

        QuantityMeasurementDTO result = service.compare(input);

        return ResponseEntity.ok(result);
    }

    /**
     * Convert quantity to another unit
     */
    @PostMapping("/convert")
    public ResponseEntity<QuantityMeasurementDTO> convert(
            @Valid @RequestBody QuantityInputDTO input) {

        QuantityMeasurementDTO result = service.convert(input);

        return ResponseEntity.ok(result);
    }

    /**
     * Add two quantities
     */
    @PostMapping("/add")
    public ResponseEntity<QuantityMeasurementDTO> add(
            @Valid @RequestBody QuantityInputDTO input) {

        QuantityMeasurementDTO result = service.add(input);

        return ResponseEntity.ok(result);
    }

    /**
     * Subtract two quantities
     */
    @PostMapping("/subtract")
    public ResponseEntity<QuantityMeasurementDTO> subtract(
            @Valid @RequestBody QuantityInputDTO input) {

        QuantityMeasurementDTO result = service.subtract(input);

        return ResponseEntity.ok(result);
    }

    /**
     * Divide two quantities
     */
    @PostMapping("/divide")
    public ResponseEntity<QuantityMeasurementDTO> divide(
            @Valid @RequestBody QuantityInputDTO input) {

        QuantityMeasurementDTO result = service.divide(input);

        return ResponseEntity.ok(result);
    }

}