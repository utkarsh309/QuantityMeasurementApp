package com.quantitymeasurementapp;

public class QuantityMeasurementApp {

    
	//Enum representing supported length units.
    public enum LengthUnit {

        FEET(12.0),      // 1 foot = 12 inches
        INCHES(1.0);     // Base unit

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

        Length length1 = new Length(1.0, LengthUnit.FEET);
        Length length2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Are lengths equal? " + length1.equals(length2));

        Length length3 = new Length(1.0, LengthUnit.INCHES);
        Length length4 = new Length(1.0, LengthUnit.INCHES);

        System.out.println("Same unit equality? " + length3.equals(length4));
    }
}
