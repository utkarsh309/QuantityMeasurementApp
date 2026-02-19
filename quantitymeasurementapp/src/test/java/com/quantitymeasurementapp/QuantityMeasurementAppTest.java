package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import com.quantitymeasurementapp.QuantityMeasurementApp.Length;
import com.quantitymeasurementapp.QuantityMeasurementApp.LengthUnit;


public class QuantityMeasurementAppTest {

	@Test
	public void testEquality_FeetToFeet_SameValue() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(1.0, LengthUnit.FEET);

	    assertTrue(l1.equals(l2));
	}

	@Test
	public void testEquality_InchToInch_SameValue() {
	    Length l1 = new Length(1.0, LengthUnit.INCHES);
	    Length l2 = new Length(1.0, LengthUnit.INCHES);

	    assertTrue(l1.equals(l2));
	}

	@Test
	public void testEquality_FeetToInch_EquivalentValue() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(12.0, LengthUnit.INCHES);

	    assertTrue(l1.equals(l2));
	}

	@Test
	public void testEquality_DifferentValue() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);
	    Length l2 = new Length(2.0, LengthUnit.FEET);

	    assertFalse(l1.equals(l2));
	}

	@Test
	public void testEquality_NullComparison() {
	    Length l1 = new Length(1.0, LengthUnit.FEET);

	    assertFalse(l1.equals(null));
	}


	
}
