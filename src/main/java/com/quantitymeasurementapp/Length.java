package com.quantitymeasurementapp;

public class Length {

	private final double value;
	private final LengthUnit unit;
	
	public Length(double value,LengthUnit unit) {
		
		if(!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}
		
		if(unit==null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		
		this.value=value;
		this.unit=unit;
	}
	
	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}
	
	public Length convertTo(LengthUnit targetUnit) {
		
		if(targetUnit==null) {
			throw new IllegalArgumentException("Target unit cannot be null.");
			
		}
		
		double baseValue=convertToBaseUnit();
		double converted=targetUnit.convertFromBaseUnit(baseValue);
		
		return new Length(converted,targetUnit);
		
	}
	
	public Length add(Length other) {
		
		if(other==null) {
			throw new IllegalArgumentException("Second operand cannot be null");
		}
		
		double sumBase=this.convertToBaseUnit() +other.convertToBaseUnit();
		
		double result=unit.convertFromBaseUnit(sumBase);
		
		return new Length(result,unit);
		
		
	}
	
	public Length add(Length other, LengthUnit targetUnit) {
		
		if (other == null)
            throw new IllegalArgumentException("Second operand cannot be null.");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null.");
        
        double sumBase =
                this.convertToBaseUnit() + other.convertToBaseUnit();

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new Length(result, targetUnit);
        
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this==obj) {
			return true;
		}
		
		if(!(obj instanceof Length)) {
			return false;
		}
		
		Length other=(Length) obj;
		
		return this.convertToBaseUnit()==other.convertToBaseUnit();
	}
	
	@Override
	public int hashCode() {
		return Double.hashCode(convertToBaseUnit());
	}
	
	@Override
	public String toString() {
		return value +" "+ unit;
	}
	
	
	
	
}
