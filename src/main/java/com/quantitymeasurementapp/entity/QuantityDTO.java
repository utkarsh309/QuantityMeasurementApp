package com.quantitymeasurementapp.entity;

public class QuantityDTO {

private double value;
private String unit;
private String type;

public QuantityDTO(double value, String unit, String type) {
    this.value = value;
    this.unit = unit;
    this.type = type;
}

public double getValue() {
    return value;
}

public String getUnit() {
    return unit;
}

public String getType() {
    return type;
}

public void setValue(double value) {
    this.value = value;
}

public void setUnit(String unit) {
    this.unit = unit;
}

public void setType(String type) {
    this.type = type;
}

@Override
public String toString() {
    return value + " " + unit;
}



}
