package com.app.quantitymeasurement.core;

/**
 * Functional interface used to determine if arithmetic
 * operations are supported for a measurement unit.
 */
@FunctionalInterface
public interface SupportsArithmetic {

    boolean isSupported();

}