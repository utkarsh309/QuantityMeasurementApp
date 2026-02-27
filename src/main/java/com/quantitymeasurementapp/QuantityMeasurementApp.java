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

        	 if (!Double.isFinite(value)) {
                 throw new IllegalArgumentException("Value must be finite.");
             }
        	
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        //Convert value to base unit (inches)
        private double convertToBaseUnit() {
            double base = value * unit.getConversionFactor();
            return Math.round(base * 100.0) / 100.0;
        }
        
        public Length convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null.");
            }

            double baseValue = convertToBaseUnit();
            double converted = baseValue / targetUnit.getConversionFactor();

            double rounded = Math.round(converted * 100.0) / 100.0;

            return new Length(rounded, targetUnit);
        }
        
        //Addition of two length object
        public Length add(Length other) {
        	
        	if(other==null) {
        		throw new IllegalArgumentException("Second Operand cannot be null");
        		
        	}
        	
        	double thisBase=this.convertToBaseUnit();
        	double otherBase=other.convertToBaseUnit();
        	
        	double sumBase=thisBase +otherBase;
        	
        	double resultValue=sumBase/this.unit.getConversionFactor();
        	
        	double rounded=Math.round(resultValue *100.0)/100.0;
        	
        	return new Length(rounded,this.unit);
        	
        }
        
     // UC7: Addition with explicit target unit
        public Length add(Length other, LengthUnit targetUnit) {

            if (other == null) {
                throw new IllegalArgumentException("Second operand cannot be null.");
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null.");
            }

            // Convert both to base unit (inches)
            double base1 = this.convertToBaseUnit();
            double base2 = other.convertToBaseUnit();

            // Add base values
            double sumBase = base1 + base2;

            // Convert to explicitly specified target unit
            double resultValue = sumBase / targetUnit.getConversionFactor();

            double rounded = Math.round(resultValue * 1000.0) / 1000.0;

            return new Length(rounded, targetUnit);
        }

        
        
        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            //null check
            if (obj == null)
                return false;

            //Type check
            if (this.getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            
            return this.convertToBaseUnit()==other.convertToBaseUnit();
        }
        
        @Override
        public int hashCode() {
            return Double.hashCode(convertToBaseUnit());
        }
        
        @Override
        public String toString() {
            return value + " " + unit;
        }
        
        
    }
    
	public static double convert(double value, LengthUnit source, LengthUnit target) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (source == null || target == null) {
			throw new IllegalArgumentException("Units cannot be null.");
		}

		double baseValue = value * source.getConversionFactor();
		double result = baseValue / target.getConversionFactor();

		return Math.round(result * 100.0) / 100.0;
	}
	
	public static Length add(Length l1,Length l2) {
		
		if(l1==null ||l2==null) {
			throw new IllegalArgumentException("Operand cannot be null");
		}
		
		return l1.add(l2);
	}
	
	public static Length add(Length l1, Length l2, LengthUnit targetUnit) {

	    if (l1 == null || l2 == null) {
	        throw new IllegalArgumentException("Operands cannot be null.");
	    }

	    return l1.add(l2, targetUnit);
	}
	
	// Demonstrate equality
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

   
    public static Length demonstrateLengthConversion(double value,
                                                     LengthUnit from,
                                                     LengthUnit to) {

        Length length = new Length(value, from);
        return length.convertTo(to);
    }

    
    public static Length demonstrateLengthConversion(Length length,
                                                     LengthUnit to) {

        return length.convertTo(to);
    }
    

    
    public static void main(String[] args) {

    	System.out.println("convert(1.0, FEET, INCHES) = "
                + convert(1.0, LengthUnit.FEET, LengthUnit.INCHES));

        System.out.println("convert(3.0, YARDS, FEET) = "
                + convert(3.0, LengthUnit.YARDS, LengthUnit.FEET));

        System.out.println("convert(36.0, INCHES, YARDS) = "
                + convert(36.0, LengthUnit.INCHES, LengthUnit.YARDS));

        System.out.println("convert(1.0, CENTIMETERS, INCHES) = "
                + convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES));
        
        Length l1=new Length(1,LengthUnit.FEET);
        Length l2=new Length(12,LengthUnit.INCHES);
        
        System.out.println("Addition of 1 feet and 12 inches "+add(l1,l2));
        
        
    }
    
}
