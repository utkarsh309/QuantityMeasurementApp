package com.app.qma_service.model;

import com.app.qma_service.enums.OperationType;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * DTO representing the input request for quantity operations.
 * 
 * It contains:
 * operand1 → first quantity
 * operand2 → second quantity (optional for some operations)
 * targetUnit → used in conversion
 * operationType → type of operation requested
 */
public class QuantityInputDTO {

    @Valid
    @NotNull(message = "Operand1 cannot be null")
    private QuantityDTO operand1;

    @Valid
    private QuantityDTO operand2;

    private String targetUnit;

    @NotNull(message = "Operation type is required")
    private OperationType operationType;

    public QuantityInputDTO() {
    }

    public QuantityInputDTO(
            QuantityDTO operand1,
            QuantityDTO operand2,
            String targetUnit,
            OperationType operationType) {

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.targetUnit = targetUnit;
        this.operationType = operationType;
    }

    public QuantityDTO getOperand1() {
        return operand1;
    }

    public void setOperand1(QuantityDTO operand1) {
        this.operand1 = operand1;
    }

    public QuantityDTO getOperand2() {
        return operand2;
    }

    public void setOperand2(QuantityDTO operand2) {
        this.operand2 = operand2;
    }

    public String getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(String targetUnit) {
        this.targetUnit = targetUnit;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
}