package com.app.qma_service.model;

import com.app.qma_service.enums.OperationType;

/**
 * DTO representing the response of a quantity measurement operation.
 *
 * This object is returned by the REST API to the client.
 */
public class QuantityMeasurementDTO {

    private OperationType operation;

    private String operand1;

    private String operand2;

    private String result;

    private boolean error;

    private String errorMessage;

    public QuantityMeasurementDTO() {
    }

    public QuantityMeasurementDTO(
            OperationType operation,
            String operand1,
            String operand2,
            String result,
            boolean error,
            String errorMessage) {

        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public OperationType getOperation() {
        return operation;
    }

    public void setOperation(OperationType operation) {
        this.operation = operation;
    }

    public String getOperand1() {
        return operand1;
    }

    public void setOperand1(String operand1) {
        this.operand1 = operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public void setOperand2(String operand2) {
        this.operand2 = operand2;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}