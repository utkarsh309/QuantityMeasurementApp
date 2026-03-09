package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class QuantityMeasurementAppTest {

	@Test
    public void testEquality_SameUnit_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_CrossUnit_YardToFeet() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_CrossUnit_YardToInches() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_CentimeterToInches() {
        Length cm = new Length(2.54, LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, LengthUnit.INCHES);

        assertTrue(cm.equals(inch));
    }

    @Test
    public void testEquality_DifferentValues() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_Null() {
        Length l1 = new Length(1.0, LengthUnit.FEET);

        assertFalse(l1.equals(null));
    }

    // hashCode Test
    @Test
    public void testHashCode_Consistency() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(36.0, LengthUnit.INCHES);

        assertEquals(l1.hashCode(), l2.hashCode());
    }

    // convert() Static API Tests
    @Test
    public void testConvert_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    public void testConvert_YardsToFeet() {
        assertEquals(9.0,
                QuantityMeasurementApp.convert(3.0,
                        LengthUnit.YARDS,
                        LengthUnit.FEET));
    }

    @Test
    public void testConvert_InchesToYards() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(36.0,
                        LengthUnit.INCHES,
                        LengthUnit.YARDS));
    }

    @Test
    public void testConvert_CentimeterToInches() {
        assertEquals(0.39,
                QuantityMeasurementApp.convert(1.0,
                        LengthUnit.CENTIMETERS,
                        LengthUnit.INCHES));
    }

    @Test
    public void testConvert_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    @Test
    public void testConvert_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES));
    }

    // convertTo() Instance Method Tests
    @Test
    public void testInstanceConvertTo() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length result = yard.convertTo(LengthUnit.FEET);

        assertEquals("3.0 FEET", result.toString());
    }
    
    //addition instance method
    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {
    	Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(2.0, LengthUnit.FEET);

        Length result = l1.add(l2);
        assertEquals(new Length(3.0, LengthUnit.FEET), result);
    }
    
    //addition static method
    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }
    
    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        Length result = l1.add(l2, LengthUnit.FEET);

        assertEquals(new Length(2.0, LengthUnit.FEET), result);
    }
    
    

    
    // Exception Tests
    @Test
    public void testInvalidValue_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Length(Double.NaN, LengthUnit.FEET));
    }

    @Test
    public void testNullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Length(1.0, null));
    }

    @Test
    public void testStaticConvert_NullUnit_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityMeasurementApp.convert(1.0, null, LengthUnit.FEET));
    }
    
 // LengthUnit Conversion Tests (UC8)


 @Test
 public void testConvertToBaseUnit_Feet() {
     assertEquals(12.0, LengthUnit.FEET.convertToBaseUnit(1.0));
 }

 @Test
 public void testConvertToBaseUnit_Inches() {
     assertEquals(12.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
 }

 @Test
 public void testConvertToBaseUnit_Yards() {
     assertEquals(36.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
 }

 @Test
 public void testConvertFromBaseUnit_Feet() {
     assertEquals(1.0, LengthUnit.FEET.convertFromBaseUnit(12.0));
 }

 @Test
 public void testConvertFromBaseUnit_Inches() {
     assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(12.0));
 }

 @Test
 public void testConvertFromBaseUnit_Yards() {
     assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(36.0));
 }
 
 //Weight Unit Test case


 // Equality Tests
 

 @Test
 public void testEquality_KilogramToKilogram() {
     Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
     Weight w2 = new Weight(1.0, WeightUnit.KILOGRAM);

     assertEquals(w1, w2);
 }

 @Test
 public void testEquality_GramToGram() {
     Weight w1 = new Weight(500.0, WeightUnit.GRAM);
     Weight w2 = new Weight(500.0, WeightUnit.GRAM);

     assertEquals(w1, w2);
 }

 @Test
 public void testEquality_PoundToPound() {
     Weight w1 = new Weight(2.0, WeightUnit.POUND);
     Weight w2 = new Weight(2.0, WeightUnit.POUND);

     assertEquals(w1, w2);
 }


 // Cross Unit Equality


 @Test
 public void testEquality_KilogramToGram() {
     Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
     Weight g = new Weight(1000.0, WeightUnit.GRAM);

     assertEquals(kg, g);
 }

 @Test
 public void testEquality_GramToPound() {
     Weight g = new Weight(453.592, WeightUnit.GRAM);
     Weight lb = new Weight(1.0, WeightUnit.POUND);

     assertEquals(g, lb);
 }


 // Conversion Tests


 @Test
 public void testConversion_KgToGram() {
     Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);

     Weight result = kg.convertTo(WeightUnit.GRAM);

     assertEquals(new Weight(1000.0, WeightUnit.GRAM), result);
 }

 @Test
 public void testConversion_KgToPound() {
     Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);

     Weight result = kg.convertTo(WeightUnit.POUND);

     assertEquals(new Weight(2.2, WeightUnit.POUND), result);
 }

 
 // Addition Tests


 @Test
 public void testAddition_KgPlusKg() {
     Weight w1 = new Weight(1.0, WeightUnit.KILOGRAM);
     Weight w2 = new Weight(2.0, WeightUnit.KILOGRAM);

     Weight result = w1.add(w2);

     assertEquals(new Weight(3.0, WeightUnit.KILOGRAM), result);
 }

 @Test
 public void testAddition_KgPlusGram() {
     Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
     Weight g = new Weight(1000.0, WeightUnit.GRAM);

     Weight result = kg.add(g);

     assertEquals(new Weight(2.0, WeightUnit.KILOGRAM), result);
 }

 @Test
 public void testAddition_TargetUnitGram() {
     Weight kg = new Weight(1.0, WeightUnit.KILOGRAM);
     Weight g = new Weight(1000.0, WeightUnit.GRAM);

     Weight result = kg.add(g, WeightUnit.GRAM);

     assertEquals(new Weight(2000.0, WeightUnit.GRAM), result);
 }


 // Category Safety Test


 @Test
 public void testWeightVsLength_Incompatible() {

     Weight weight = new Weight(1.0, WeightUnit.KILOGRAM);

     Length length = new Length(1.0, LengthUnit.FEET);

     assertFalse(weight.equals(length));
 }


 // Edge Cases


 @Test
 public void testEquality_ZeroValue() {
     Weight w1 = new Weight(0.0, WeightUnit.KILOGRAM);
     Weight w2 = new Weight(0.0, WeightUnit.GRAM);

     assertEquals(w1, w2);
 }

 @Test
 public void testEquality_NegativeWeight() {
     Weight w1 = new Weight(-1.0, WeightUnit.KILOGRAM);
     Weight w2 = new Weight(-1000.0, WeightUnit.GRAM);

     assertEquals(w1, w2);
 }

 
 
 
	
}
