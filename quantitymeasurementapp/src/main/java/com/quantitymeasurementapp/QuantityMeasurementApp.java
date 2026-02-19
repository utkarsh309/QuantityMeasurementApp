package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

    
	//Enum representing supported length units.
    public enum LengthUnit {

    	FEET(12.0),              // 1 foot = 12 inches
        INCHES(1.0),             // Base unit
        YARDS(36.0),             // 1 yard = 36 inches
        CENTIMETERS(0.393701);   // 1 cm = 0.393701 inches

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    //Generic Length class handling all unit types
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        //Convert value to base unit (inches)
        private double convertToBaseUnit() {
            return this.value * unit.getConversionFactor();
        }

        
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (this.getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            return Double.compare(
                    this.convertToBaseUnit(),
                    other.convertToBaseUnit()) == 0;
        }
    }

    
    public static void main(String[] args) {

    	// Yard to Feet
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(3.0, LengthUnit.FEET);
        System.out.println("1 yard == 3 feet ? " + l1.equals(l2));

        // Yard to Inches
        Length l3 = new Length(1.0, LengthUnit.YARDS);
        Length l4 = new Length(36.0, LengthUnit.INCHES);
        System.out.println("1 yard == 36 inches ? " + l3.equals(l4));

        // CM to Inches
        Length l5 = new Length(1.0, LengthUnit.CENTIMETERS);
        Length l6 = new Length(0.393701, LengthUnit.INCHES);
        System.out.println("1 cm == 0.393701 inches ? " + l5.equals(l6));

        // Multi-unit
        Length l7 = new Length(2.0, LengthUnit.YARDS);
        Length l8 = new Length(72.0, LengthUnit.INCHES);
        System.out.println("2 yards == 72 inches ? " + l7.equals(l8));
    }
}
