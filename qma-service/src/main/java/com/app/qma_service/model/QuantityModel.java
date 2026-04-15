package com.app.qma_service.model;

import com.app.qma_service.core.IMeasurable;

/**
 * Internal model representing a quantity used by the service layer.
 * 
 * This class connects the DTO layer with the core measurement logic.
 */
public class QuantityModel<U extends IMeasurable> {

    private double value;

    private U unit;

    public QuantityModel() {
    }

    public QuantityModel(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(U unit) {
        this.unit = unit;
    }
}