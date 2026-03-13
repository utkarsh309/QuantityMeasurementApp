package com.quantitymeasurementapp.core;

public enum TemperatureUnit implements IMeasurable {

	CELSIUS(false),
	FAHRENHEIT(true);
	
	private boolean isFahrenhiet;
	
	TemperatureUnit(boolean isFahrenheit){
		this.isFahrenhiet=isFahrenheit;
		
	}
	
	//conversion to base (Celsius)
	public double convertToBaseUnit(double value) {
		if(this==FAHRENHEIT) {
			return (value - 32) *5 / 9;
		}
		return value;
	}
	
	public double convertFromBaseUnit(double baseValue) {

        if (this == FAHRENHEIT) {
            return (baseValue * 9 / 5) + 32;
        }

        return baseValue;
    }
	
	// temperature does not support arithmetic
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
