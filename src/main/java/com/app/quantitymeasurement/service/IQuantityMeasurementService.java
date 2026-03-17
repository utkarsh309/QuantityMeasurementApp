package com.app.quantitymeasurement.service;

import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;

/**
 * Service interface defining quantity measurement operations.
 *
 * This layer contains the business logic and interacts with
 * the repository layer for persistence.
 */
public interface IQuantityMeasurementService {

    /**
     * Perform quantity comparison.
     */
    QuantityMeasurementDTO compare(QuantityInputDTO input);

    /**
     * Perform unit conversion.
     */
    QuantityMeasurementDTO convert(QuantityInputDTO input);

    /**
     * Perform addition of two quantities.
     */
    QuantityMeasurementDTO add(QuantityInputDTO input);

    /**
     * Perform subtraction of two quantities.
     */
    QuantityMeasurementDTO subtract(QuantityInputDTO input);

    /**
     * Perform division of two quantities.
     */
    QuantityMeasurementDTO divide(QuantityInputDTO input);

}