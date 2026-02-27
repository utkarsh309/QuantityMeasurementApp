package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import com.quantitymeasurementapp.QuantityMeasurementApp.Length;
import com.quantitymeasurementapp.QuantityMeasurementApp.LengthUnit;


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
    
    
    // Overloaded Demonstration Methods

    @Test
    public void testDemonstrateLengthConversion_RawInput() {
        Length result =
                QuantityMeasurementApp.demonstrateLengthConversion(
                        1.0,
                        LengthUnit.FEET,
                        LengthUnit.INCHES);

        assertEquals("12.0 INCHES", result.toString());
    }

    @Test
    public void testDemonstrateLengthConversion_ObjectInput() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        Length result =
                QuantityMeasurementApp.demonstrateLengthConversion(
                        yard,
                        LengthUnit.FEET);

        assertEquals("3.0 FEET", result.toString());
    }

    @Test
    public void testDemonstrateLengthEquality() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(
                QuantityMeasurementApp.demonstrateLengthEquality(yard, feet)
        );
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
	
}
