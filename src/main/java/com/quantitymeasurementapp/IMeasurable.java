package com.quantitymeasurementapp;

public interface IMeasurable {

	double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    String getUnitName();
    
    SupportsArithmetic supportsArithmetic = () -> true;
    
    //Default method
    default boolean supportsArithmetic() {
    	return supportsArithmetic.isSupported();
    }
    
    //Default validation method
    default void validateOperationSupport(String operation) {
    	
    }
}
