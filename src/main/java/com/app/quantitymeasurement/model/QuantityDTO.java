package com.app.quantitymeasurement.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * DTO representing a quantity with value, unit, and measurement type.
 *
 * This class is used to transfer quantity data between the API layer
 * and the service layer.
 */
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotEmpty(message = "Unit cannot be empty")
    private String unit;

    @NotEmpty(message = "Measurement type cannot be empty")
    private String type;

    public QuantityDTO() {
    }

    public QuantityDTO(Double value, String unit, String type) {
        this.value = value;
        this.unit = unit;
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

}