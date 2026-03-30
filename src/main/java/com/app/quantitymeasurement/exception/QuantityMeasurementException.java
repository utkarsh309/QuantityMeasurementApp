package com.app.quantitymeasurement.exception;

/**
 * Custom exception for Quantity Measurement application.
 * 
 * Used to represent domain-specific errors such as
 * invalid units, unsupported measurement types, etc.
 */
public class QuantityMeasurementException extends RuntimeException {

    public QuantityMeasurementException(String message) {
        super(message);
    }

    public QuantityMeasurementException(String message, Throwable cause) {
        super(message, cause);
    }
}