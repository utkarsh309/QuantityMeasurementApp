package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import com.quantitymeasurementapp.QuantityMeasurementApp.Length;
import com.quantitymeasurementapp.QuantityMeasurementApp.LengthUnit;


public class QuantityMeasurementAppTest {

	//Yard Test
	@Test
    public void testEquality_YardToYard_SameValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(1.0, LengthUnit.YARDS);

        assertTrue(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        Length l1 = new Length(1.0, LengthUnit.YARDS);
        Length l2 = new Length(2.0, LengthUnit.YARDS);

        assertFalse(l1.equals(l2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(feet.equals(yard)); // symmetry
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(2.0, LengthUnit.FEET);

        assertFalse(yard.equals(feet));
    }

    
    // CENTIMETER TESTS
   
    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        Length cm1 = new Length(2.0, LengthUnit.CENTIMETERS);
        Length cm2 = new Length(2.0, LengthUnit.CENTIMETERS);

        assertTrue(cm1.equals(cm2));
    }

    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length inches = new Length(0.393701, LengthUnit.INCHES);

        assertTrue(cm.equals(inches));
    }

    @Test
    public void testEquality_CentimeterToFeet_NonEquivalentValue() {
        Length cm = new Length(1.0, LengthUnit.CENTIMETERS);
        Length feet = new Length(1.0, LengthUnit.FEET);

        assertFalse(cm.equals(feet));
    }

    
    // TRANSITIVE PROPERTY

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {

        Length yard = new Length(1.0, LengthUnit.YARDS);
        Length feet = new Length(3.0, LengthUnit.FEET);
        Length inches = new Length(36.0, LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches)); // transitive
    }

   
    // NULL & REFLEXIVE TESTS
    @Test
    public void testEquality_SameReference() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertTrue(yard.equals(yard));
    }

    @Test
    public void testEquality_NullComparison() {
        Length yard = new Length(1.0, LengthUnit.YARDS);

        assertFalse(yard.equals(null));
    }

    @Test
    public void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Length(1.0, null);
        });
    }

    
    // COMPLEX SCENARIO
    @Test
    public void testEquality_AllUnits_ComplexScenario() {

        Length yards = new Length(2.0, LengthUnit.YARDS);
        Length feet = new Length(6.0, LengthUnit.FEET);
        Length inches = new Length(72.0, LengthUnit.INCHES);

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }

	
}
