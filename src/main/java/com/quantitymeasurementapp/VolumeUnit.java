package com.quantitymeasurementapp;

public enum VolumeUnit implements IMeasurable{
	

	LITER(1.0),
	MILLILITER(0.001),
	GALLON(3.785);
	
	private final double conversionfactor;
	
	
	VolumeUnit(double conversionFactor){
		this.conversionfactor=conversionFactor;
	}
	
	
	
	@Override
	public double getConversionFactor() {
		return conversionfactor;
	}

	@Override
	public double convertToBaseUnit(double value) {
		return value * conversionfactor;
	}

	@Override
	public double convertFromBaseUnit(double baseValue) {
		return baseValue / conversionfactor;
	}

	@Override
	public String getUnitName() {
		return this.name();
	}
	
	
	
}