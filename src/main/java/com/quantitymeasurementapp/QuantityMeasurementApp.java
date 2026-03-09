package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

    public static double convert(double value,LengthUnit source,LengthUnit target) {
    	
    	if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite.");

        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null.");

        double baseValue = source.convertToBaseUnit(value);

        return target.convertFromBaseUnit(baseValue);
    }
    
    public static Length add(Length l1, Length l2) {

        if (l1 == null || l2 == null)
            throw new IllegalArgumentException("Operand cannot be null");

        return l1.add(l2);
    }
    
	public static Length add(Length l1, Length l2, LengthUnit targetUnit) {

		if (l1 == null || l2 == null)
			throw new IllegalArgumentException("Operands cannot be null.");

		return l1.add(l2, targetUnit);
	}
    
	
	public static void main(String[] args) {

        System.out.println("convert(1.0, FEET, INCHES) = "
                + convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println("convert(3.0, YARDS, FEET) = "
                + convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        Length l1 = new Length(1, LengthUnit.FEET);
        Length l2 = new Length(12, LengthUnit.INCHES);

        System.out.println("Addition of 1 feet and 12 inches = "
                + add(l1, l2));
    }
}
