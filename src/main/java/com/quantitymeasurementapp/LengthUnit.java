package com.quantitymeasurementapp;

public enum LengthUnit{
	
	FEET(12.0),              // 1 foot = 12 inches
    INCHES(1.0),             // Base unit
    YARDS(36.0),             // 1 yard = 36 inches
    CENTIMETERS(0.393701);   // 1 cm = 0.393701 inches
	
	private final double conversionFactor;
	
	LengthUnit(double conversionFactor){
		
		this.conversionFactor=conversionFactor;
	}
	
	public double getConversionFactor() {
		return conversionFactor;
	}
	
	 // Convert value -> base unit (inches)
	public double convertToBaseUnit(double value) {
		double base= value *conversionFactor;
		return Math.round(base * 100.0)/100.0;
	}
	
	// Convert base unit -> this unit
	public double convertFromBaseUnit(double baseValue) {
		double result= baseValue / conversionFactor;
		return Math.round(result *100.0)/100.0;
	}
	
	
	
	
	
}