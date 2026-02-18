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

	//Main method
	public static void main(String[] args) {
		
		Feet firstValue=new Feet(1.0);
		Feet secondValue=new Feet(1.0);
		
		boolean result=firstValue.equals(secondValue);
		
		System.out.println("Are values equal? "+ result);
		
	}

}
