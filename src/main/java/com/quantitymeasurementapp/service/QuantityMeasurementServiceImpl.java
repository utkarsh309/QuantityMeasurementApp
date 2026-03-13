package com.quantitymeasurementapp.service;

import com.quantitymeasurementapp.core.IMeasurable;
import com.quantitymeasurementapp.core.LengthUnit;
import com.quantitymeasurementapp.core.Quantity;
import com.quantitymeasurementapp.core.TemperatureUnit;
import com.quantitymeasurementapp.core.VolumeUnit;
import com.quantitymeasurementapp.core.WeightUnit;
import com.quantitymeasurementapp.entity.QuantityDTO;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
		implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;

    //constructor
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

// ---------- Equality ----------

@Override
public boolean compare(QuantityDTO q1, QuantityDTO q2) {

    IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
    IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

    Quantity<IMeasurable> a =
            new Quantity<>(q1.getValue(), unit1);

    Quantity<IMeasurable> b =
            new Quantity<>(q2.getValue(), unit2);

    return a.equals(b);
}

// ---------- Conversion ----------

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

    return new QuantityDTO(
            result.getValue(),
            result.getUnit().getUnitName(),
            q.getType());
}

// ---------- Addition ----------

@Override
public QuantityDTO add(QuantityDTO q1, QuantityDTO q2) {

    IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
    IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

    Quantity<IMeasurable> a =
            new Quantity<>(q1.getValue(), unit1);

    Quantity<IMeasurable> b =
            new Quantity<>(q2.getValue(), unit2);

    Quantity<IMeasurable> result = a.add(b);

    return new QuantityDTO(
            result.getValue(),
            result.getUnit().getUnitName(),
            q1.getType());
}

// ---------- Subtraction ----------

@Override
public QuantityDTO subtract(QuantityDTO q1, QuantityDTO q2) {

    IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
    IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

    Quantity<IMeasurable> a =
            new Quantity<>(q1.getValue(), unit1);

    Quantity<IMeasurable> b =
            new Quantity<>(q2.getValue(), unit2);

    Quantity<IMeasurable> result = a.subtract(b);

    return new QuantityDTO(
            result.getValue(),
            result.getUnit().getUnitName(),
            q1.getType());
}

// ---------- Division ----------

@Override
public double divide(QuantityDTO q1, QuantityDTO q2) {

    IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
    IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

    Quantity<IMeasurable> a =
            new Quantity<>(q1.getValue(), unit1);

    Quantity<IMeasurable> b =
            new Quantity<>(q2.getValue(), unit2);

    return a.divide(b);
}


}
