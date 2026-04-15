package com.app.qma_service.core;

/**
 * Enum representing length measurement units.
 *
 * Base unit: INCHES
 */
public enum LengthUnit implements IMeasurable {

    FEET(12.0),             // 1 foot = 12 inches
    INCHES(1.0),            // base unit
    YARDS(36.0),            // 1 yard = 36 inches
    CENTIMETERS(0.393701);  // 1 cm = 0.393701 inches

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Convert a value to the base unit (INCHES)
     */
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    /**
     * Convert from base unit (INCHES) to this unit
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }
}