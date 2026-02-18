package com.quantitymeasurementapp;

public class QuantityMeasurementApp {
	
	//Inner class representing Feet measurement
	public static class Feet{
		
		private final double value;
		
		//Constructor
		public Feet(double value) {
			this.value=value;
		}
		
		//getter method
		public double getValue() {
			return value;
		}
		
		@Override
		public boolean equals(Object obj) {
			
			//Same reference
			if(this==obj) {
				return true;
			}
			
			//Null check
			if(obj==null) {
				return false;
			}
			
			//Type Check
			if(this.getClass()!=obj.getClass()) {
				return false;
			}
			
			//Cast safety
			Feet other=(Feet)obj;
			
			//Compare using Double.compare()
			return Double.compare(this.value, other.value)==0;
		}
		
		
	}
	
	//Inner class representing Inch measurement
	public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (this.getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }
        
        
    }
	
	public static void demonstrateFeetEquality() {

        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);

        System.out.println("Feet Equality: " + value1.equals(value2));
    }
	
	public static void demonstrateInchesEquality() {

        Inches value1 = new Inches(1.0);
        Inches value2 = new Inches(1.0);

        System.out.println("Inches Equality: " + value1.equals(value2));
    }

	//Main method
	public static void main(String[] args) {
		
		demonstrateFeetEquality();
        demonstrateInchesEquality();
		
	}

}
