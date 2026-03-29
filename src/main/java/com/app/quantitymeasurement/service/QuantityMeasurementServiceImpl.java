package com.app.quantitymeasurement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantitymeasurement.core.IMeasurable;
import com.app.quantitymeasurement.core.LengthUnit;
import com.app.quantitymeasurement.core.Quantity;
import com.app.quantitymeasurement.core.TemperatureUnit;
import com.app.quantitymeasurement.core.VolumeUnit;
import com.app.quantitymeasurement.core.WeightUnit;
import com.app.quantitymeasurement.enums.OperationType;
import com.app.quantitymeasurement.model.QuantityDTO;
import com.app.quantitymeasurement.model.QuantityInputDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementDTO;
import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

/**
 * Service implementation containing business logic
 * for quantity measurement operations.
 */
@Service
public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    @Autowired
    private QuantityMeasurementRepository repository;

    /**
     * Helper method to determine correct unit enum
     */
    private IMeasurable getUnit(String type, String unitName) {

        switch (type.toUpperCase()) {

            case "LENGTH":
                return LengthUnit.valueOf(unitName);

            case "WEIGHT":
                return WeightUnit.valueOf(unitName);

            case "VOLUME":
                return VolumeUnit.valueOf(unitName);

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unitName);

            default:
                throw new IllegalArgumentException("Unsupported measurement type");
        }
    }

    /**
     * Compare quantities
     */
    @Override
    public QuantityMeasurementDTO compare(QuantityInputDTO input) {

        QuantityDTO q1 = input.getOperand1();
        QuantityDTO q2 = input.getOperand2();

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), unit1);
        Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), unit2);

        boolean result = a.equals(b);

        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity(
                        OperationType.COMPARE,
                        q1.toString(),
                        q2.toString(),
                        String.valueOf(result),
                        false,
                        null
                );

        repository.save(entity);

        return new QuantityMeasurementDTO(
                OperationType.COMPARE,
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                false,
                null
        );
    }

    /**
     * Convert quantity
     */
    @Override
    public QuantityMeasurementDTO convert(QuantityInputDTO input) {

        QuantityDTO q = input.getOperand1();

        IMeasurable sourceUnit =
                getUnit(q.getType(), q.getUnit());

        IMeasurable targetUnit =
                getUnit(q.getType(), input.getTargetUnit());

        Quantity<IMeasurable> quantity =
                new Quantity<>(q.getValue(), sourceUnit);

        Quantity<IMeasurable> result =
                quantity.convertTo(targetUnit);

        String resultValue = result.toString();

        repository.save(new QuantityMeasurementEntity(
                OperationType.CONVERT,
                q.toString(),
                input.getTargetUnit(),
                resultValue,
                false,
                null
        ));

        return new QuantityMeasurementDTO(
                OperationType.CONVERT,
                q.toString(),
                input.getTargetUnit(),
                resultValue,
                false,
                null
        );
    }

    /**
     * Add quantities
     */
    @Override
    public QuantityMeasurementDTO add(QuantityInputDTO input) {

        QuantityDTO q1 = input.getOperand1();
        QuantityDTO q2 = input.getOperand2();

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = a.add(b);

        repository.save(new QuantityMeasurementEntity(
                OperationType.ADD,
                q1.toString(),
                q2.toString(),
                result.toString(),
                false,
                null
        ));

        return new QuantityMeasurementDTO(
                OperationType.ADD,
                q1.toString(),
                q2.toString(),
                result.toString(),
                false,
                null
        );
    }

    /**
     * Subtract quantities
     */
    @Override
    public QuantityMeasurementDTO subtract(QuantityInputDTO input) {

        QuantityDTO q1 = input.getOperand1();
        QuantityDTO q2 = input.getOperand2();

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = a.subtract(b);

        repository.save(new QuantityMeasurementEntity(
                OperationType.SUBTRACT,
                q1.toString(),
                q2.toString(),
                result.toString(),
                false,
                null
        ));

        return new QuantityMeasurementDTO(
                OperationType.SUBTRACT,
                q1.toString(),
                q2.toString(),
                result.toString(),
                false,
                null
        );
    }

    /**
     * Divide quantities
     */
    @Override
    public QuantityMeasurementDTO divide(QuantityInputDTO input) {

        QuantityDTO q1 = input.getOperand1();
        QuantityDTO q2 = input.getOperand2();

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        double result = a.divide(b);

        repository.save(new QuantityMeasurementEntity(
                OperationType.DIVIDE,
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                false,
                null
        ));

        return new QuantityMeasurementDTO(
                OperationType.DIVIDE,
                q1.toString(),
                q2.toString(),
                String.valueOf(result),
                false,
                null
        );
    }

}