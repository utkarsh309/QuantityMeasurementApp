package com.app.quantitymeasurement.core;

/**
 * Interface defining behaviour of measurable units.
 *
 * Every measurement unit (Length, Weight, Volume, Temperature)
 * must implement this interface.
 */
public interface IMeasurable {

    /**
     * Returns the conversion factor relative to base unit.
     */
    double getConversionFactor();

    /**
     * Converts a value to the base unit of the measurement.
     */
    double convertToBaseUnit(double value);

    /**
     * Converts from base unit to this unit.
     */
    double convertFromBaseUnit(double baseValue);

    /**
     * Returns the unit name.
     */
    String getUnitName();

    /**
     * Defines whether arithmetic operations are supported.
     */
    SupportsArithmetic supportsArithmetic = () -> true;

    /**
     * Default method checking arithmetic support.
     */
    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    /**
     * Default validation method for arithmetic operations.
     */
    default void validateOperationSupport(String operation) {
        // Default does nothing
    }
}