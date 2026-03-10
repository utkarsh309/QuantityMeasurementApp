package com.quantitymeasurementapp;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite.");
        }

        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null.");
        }

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBaseUnit() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {

        double base = unit.convertToBaseUnit(value);

        double result = targetUnit.convertFromBaseUnit(base);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
    	
    	if(other==null) {
    		throw new IllegalArgumentException("Other Quantity cannot be null");
    	}

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double sum = base1 + base2;

        double result = unit.convertFromBaseUnit(sum);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
    	
    	if(other==null) {
    		throw new IllegalArgumentException("Other Quantity cannot be null");
    	}
    	
    	if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base1 = this.toBaseUnit();
        double base2 = other.toBaseUnit();

        double sum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sum);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other){
    	
    	if(other==null) {
    		throw new IllegalArgumentException("Other Quantity cannot be null");
    	}
    	
    	if(!this.unit.getClass().equals(other.unit.getClass())) {
    		throw new IllegalArgumentException("Different measurement categories");
    	}
    	
    	double base1=this.unit.convertToBaseUnit(value);
    	double base2=other.unit.convertToBaseUnit(other.value);
    	
    	double resultBase=base1-base2;
    	
    	double converted=unit.convertFromBaseUnit(resultBase);
    	
    	double rounded=Math.round(converted *100.0 )/100.0 ;
    	
    	return new Quantity<>(rounded,unit);
    	
    }
    
    public Quantity<U> subtract(Quantity<U>other, U targetUnit){
    	
    	if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }
        
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double resultBase = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(resultBase);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
        
    }
    
    public double divide(Quantity<U> other) {
    	
    	if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }
        
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        
        if (base2 == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return base1/base2;
    	
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other)) return false;

        if (!this.unit.getClass().equals(other.unit.getClass()))
            return false;

        return Double.compare(
                this.toBaseUnit(),
                other.unit.convertToBaseUnit(other.value)
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}
