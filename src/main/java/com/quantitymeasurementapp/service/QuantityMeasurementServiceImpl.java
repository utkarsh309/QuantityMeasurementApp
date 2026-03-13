package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.core.IMeasurable;
import com.quantitymeasurementapp.core.LengthUnit;
import com.quantitymeasurementapp.core.Quantity;
import com.quantitymeasurementapp.core.TemperatureUnit;
import com.quantitymeasurementapp.core.VolumeUnit;
import com.quantitymeasurementapp.core.WeightUnit;
import com.quantitymeasurementapp.entity.QuantityDTO;
import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    // ---------- Helper method ----------
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
                throw new IllegalArgumentException(
                        "Unsupported measurement type");
        }
    }

    // ---------- Compare ----------

    @Override
    public boolean compare(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        boolean result = a.equals(b);

        repository.save(new QuantityMeasurementEntity(
                "COMPARE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }

    // ---------- Convert ----------

    @Override
    public QuantityDTO convert(QuantityDTO q, String targetUnit) {

        IMeasurable sourceUnit =
                getUnit(q.getType(), q.getUnit());

        IMeasurable target =
                getUnit(q.getType(), targetUnit);

        Quantity<IMeasurable> quantity =
                new Quantity<>(q.getValue(), sourceUnit);

        Quantity<IMeasurable> result =
                quantity.convertTo(target);

        QuantityDTO dto = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                q.getType());

        repository.save(new QuantityMeasurementEntity(
                "CONVERT",
                q.toString(),
                targetUnit,
                dto.toString()
        ));

        return dto;
    }

    // ---------- Add ----------

    @Override
    public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = a.add(b);

        QuantityDTO dto = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                q1.getType());

        repository.save(new QuantityMeasurementEntity(
                "ADD",
                q1.toString(),
                q2.toString(),
                dto.toString()
        ));

        return dto;
    }

    // ---------- Subtract ----------

    @Override
    public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        Quantity<IMeasurable> result = a.subtract(b);

        QuantityDTO dto = new QuantityDTO(
                result.getValue(),
                result.getUnit().getUnitName(),
                q1.getType());

        repository.save(new QuantityMeasurementEntity(
                "SUBTRACT",
                q1.toString(),
                q2.toString(),
                dto.toString()
        ));

        return dto;
    }

    // ---------- Divide ----------

    @Override
    public double divide(QuantityDTO q1, QuantityDTO q2) {

        IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
        IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

        Quantity<IMeasurable> a =
                new Quantity<>(q1.getValue(), unit1);

        Quantity<IMeasurable> b =
                new Quantity<>(q2.getValue(), unit2);

        double result = a.divide(b);

        repository.save(new QuantityMeasurementEntity(
                "DIVIDE",
                q1.toString(),
                q2.toString(),
                String.valueOf(result)
        ));

        return result;
    }
}