package com.quantitymeasurementapp;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.quantitymeasurementapp.QuantityMeasurementApp.Feet;
import com.quantitymeasurementapp.QuantityMeasurementApp.Inches;


public class QuantityMeasurementAppTest {

	@Test
    public void testFeetEquality_SameValue() {
        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(1.0);

        assertTrue(value1.equals(value2));
      }

    @Test
    public void testFeetEquality_DifferentValue() {
        Feet value1 = new Feet(1.0);
        Feet value2 = new Feet(2.0);

        assertFalse(value1.equals(value2));
                
    }

    @Test
    public void testFeetEquality_NullComparison() {
        Feet value = new Feet(1.0);

        assertFalse(value.equals(null));
                
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        Feet value = new Feet(1.0);
        String otherObject = "1.0";

        assertFalse(value.equals(otherObject));
    }

    @Test
    public void testFeetEquality_SameReference() {
        Feet value = new Feet(1.0);

        assertTrue(value.equals(value));
    }
    
    @Test
    public void testInchesEquality_SameValue() {
        Inches value1 = new Inches(1.0);
        Inches value2 = new Inches(1.0);

        assertTrue(value1.equals(value2));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        Inches value1 = new Inches(1.0);
        Inches value2 = new Inches(2.0);

        assertFalse(value1.equals(value2));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        Inches value = new Inches(1.0);

        assertFalse(value.equals(null));
    }

    @Test
    public void testInchesEquality_SameReference() {
        Inches value = new Inches(1.0);

        assertTrue(value.equals(value));
    }

	
}
