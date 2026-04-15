package com.app.qma_service.core;

/**
 * Enum representing volume measurement units.
 *
 * Base unit: LITER
 */
public enum VolumeUnit implements IMeasurable {

    LITER(1.0),
    MILLILITER(0.001),
    GALLON(3.785);

    private final double conversionFactor;

    VolumeUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Convert value to base unit (LITER)
     */
    @Override
    public double convertToBaseUnit(double value) {
        return value * conversionFactor;
    }

    /**
     * Convert from base unit (LITER) to this unit
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