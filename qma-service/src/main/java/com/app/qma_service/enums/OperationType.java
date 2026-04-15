package com.app.qma_service.enums;

/**
 * Enum representing all supported quantity measurement operations.
 *
 * Using an enum ensures type safety and avoids using raw strings
 * throughout the application when referring to operations.
 */
public enum OperationType {

    ADD,
    SUBTRACT,
    DIVIDE,
    COMPARE,
    CONVERT;

    /**
     * Converts a string to OperationType safely.
     *
     * @param operation string value
     * @return OperationType
     */
    public static OperationType fromString(String operation) {

        for (OperationType op : OperationType.values()) {

            if (op.name().equalsIgnoreCase(operation)) {
                return op;
            }

        }

        throw new IllegalArgumentException(
                "Invalid operation type: " + operation
        );
    }

}