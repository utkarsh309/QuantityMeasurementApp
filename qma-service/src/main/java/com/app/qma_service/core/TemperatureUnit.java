package com.app.qma_service.core;

/**
 * Enum representing temperature measurement units.
 *
 * Base unit: CELSIUS
 */
public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true);

    private boolean isFahrenheit;

    TemperatureUnit(boolean isFahrenheit) {
        this.isFahrenheit = isFahrenheit;
    }

    /**
     * Convert value to base unit (CELSIUS)
     */
    @Override
    public double convertToBaseUnit(double value) {

        if (this == FAHRENHEIT) {
            return (value - 32) * 5 / 9;
        }

        return value;
    }

    /**
     * Convert from base unit (CELSIUS)
     */
    @Override
    public double convertFromBaseUnit(double baseValue) {

        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5) + 32;
        }

        return baseValue;
    }

    /**
     * Temperature does not support arithmetic operations
     */
    @Override
    public boolean supportsArithmetic() {
        return false;
    }

    @Override
    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic()) {

            throw new UnsupportedOperationException(
                    this.name() + " does not support " + operation
            );
        }
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }
}