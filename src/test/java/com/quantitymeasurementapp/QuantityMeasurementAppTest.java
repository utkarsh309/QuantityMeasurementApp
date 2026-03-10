package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QuantityMeasurementAppTest {

    
    // Equality Tests (Length)
    

    @Test
    public void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }
    
    


    // Equality Tests (Weight)
   

    @Test
    public void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }
    
    //Equality Tests(Volume)
    @Test
    public void testGenericQuantity_VolumeEquality() {
    	
    	Quantity<VolumeUnit> ml=new Quantity<>(1000.0,VolumeUnit.MILLILITER);
    	
    	Quantity<VolumeUnit> ltr=new Quantity<>(1.0, VolumeUnit.LITER);
    	
    	assertEquals(ml, ltr);
    }
    
    


  
    // Conversion Tests (Length)
   

    @Test
    public void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }


    // Conversion Tests (Weight)
 

    @Test
    public void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }
    
    // conversion Tests(Volume)

    @Test
    public void testGenericQuantity_VolumeConversion() {
    	
    	Quantity<VolumeUnit> ml=new Quantity<>(1000.0,VolumeUnit.MILLILITER);
    	
    	Quantity<VolumeUnit> result=ml.convertTo(VolumeUnit.LITER);
    	
    	assertEquals(new Quantity<>(1.0,VolumeUnit.LITER),result);

    	
    }

    
    // Addition Tests (Length)
   

    @Test
    public void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                feet.add(inches, LengthUnit.FEET);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }


    // Addition Tests (Weight)
  

    @Test
    public void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(gram, WeightUnit.KILOGRAM);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    //Addition Tests(Volume)
    
    @Test 
    public void testGenericQuantity_VolumeAddition() {
    	
		Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITER);

		Quantity<VolumeUnit> ltr = new Quantity<>(1.0, VolumeUnit.LITER);
		
		Quantity<VolumeUnit> result=ml.add(ltr,VolumeUnit.LITER);
		
		assertEquals(new Quantity<>(2.0,VolumeUnit.LITER),result);
    	
    	
    }
    
    //Subtraction Tests
    @Test
    public void testSubtraction_sameUnit() {
    	
    	Quantity<LengthUnit> l1=new Quantity<>(10,LengthUnit.FEET);
    	Quantity<LengthUnit> l2=new Quantity<>(5,LengthUnit.FEET);
    	
    	assertEquals(new Quantity<>(5,LengthUnit.FEET),l1.subtract(l2));
    	
    }
    
    @Test
    public void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6, LengthUnit.INCHES);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), q1.subtract(q2));
    }
    
    //Divison Tests
    @Test
    public void testDivision_SameUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2));
    }
    
    @Test
    public void testDivision_CrossUnit() {

        Quantity<LengthUnit> q1 = new Quantity<>(24, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2));
    }
    
    @Test
    public void testDivision_ByZero() {

        Quantity<LengthUnit> q1 = new Quantity<>(10, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> q1.divide(q2));
    }

 
    // Cross Category Safety


    @Test
    public void testCrossCategoryPrevention_LengthVsWeight() {

        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // HashCode Consistency
   

    @Test
    public void testHashCodeConsistency() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet.hashCode(), inches.hashCode());
    }

    // Constructor Validation
    

    @Test
    public void testConstructorValidation_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }


    @Test
    public void testConstructorValidation_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
    
   

}