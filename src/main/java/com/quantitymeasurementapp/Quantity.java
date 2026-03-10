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
    	
    	validateArithmeticOperands(other, null, false);
    	
    	double resultBase=performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(resultBase);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
    	
    	validateArithmeticOperands(other, targetUnit, true);
    	
    	double resultBase=performBaseArithmetic(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
    }
    
    public Quantity<U> subtract(Quantity<U> other){
    	
    	validateArithmeticOperands(other, null, false);
    	
    	double resultBase=performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
    	
    	double converted=unit.convertFromBaseUnit(resultBase);
    	
    	double rounded=Math.round(converted *100.0 )/100.0 ;
    	
    	return new Quantity<>(rounded,unit);
    	
    }
    
    public Quantity<U> subtract(Quantity<U>other, U targetUnit){
    	
    	validateArithmeticOperands(other, targetUnit, true);
    	
    	double resultBase=performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(resultBase);

        double rounded = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(rounded, targetUnit);
        
    }
    
    public double divide(Quantity<U> other) {
    	
    	validateArithmeticOperands(other, null, false);
    	
    	double resultBase=performBaseArithmetic(other, ArithmeticOperation.DIVIDE);

        return resultBase;
    	
    }
    
    // Private Helper Method
    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired){
    	
    	if(other ==null) {
    		throw new IllegalArgumentException("Operand cannot be null");
    	}
    	
    	if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        if (!Double.isFinite(value) || !Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Values must be finite");
        }
        
        if (targetUnitRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit required");
        }
        
    }
    
    private double performBaseArithmetic(Quantity<U> other,ArithmeticOperation operation) {
    	
    	double base1=unit.convertToBaseUnit(value);
    	double base2=other.unit.convertToBaseUnit(other.value);
    	
    	return operation.compute(base1, base2);
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
    
    //Enum
    private enum ArithmeticOperation{
    	
    	ADD{
    		@Override
    		public double compute(double a ,double b) {
    			return a + b;
    		}
    	},
    	SUBTRACT{
    		@Override
    		public double compute(double a , double b) {
    			return a - b;
    		}
    	},
    	DIVIDE{
    		@Override
    		public double compute(double a ,double b) {
    			if(b==0) {
    				throw new ArithmeticException("Divide by zero");
    			}
    			return a / b;
    		}
    	};
    	
    	public abstract double compute(double a , double b);
    	
    }
}
