package com.app.qma_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.qma_service.core.IMeasurable;
import com.app.qma_service.core.LengthUnit;
import com.app.qma_service.core.Quantity;
import com.app.qma_service.core.TemperatureUnit;
import com.app.qma_service.core.VolumeUnit;
import com.app.qma_service.core.WeightUnit;
import com.app.qma_service.enums.OperationType;
import com.app.qma_service.model.QuantityDTO;
import com.app.qma_service.model.QuantityInputDTO;
import com.app.qma_service.model.QuantityMeasurementDTO;
import com.app.qma_service.model.QuantityMeasurementEntity;
import com.app.qma_service.repository.QuantityMeasurementRepository;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired
	private QuantityMeasurementRepository repository;

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

	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input, String username) {

		QuantityDTO q1 = input.getOperand1();
		QuantityDTO q2 = input.getOperand2();

		IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

		Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), unit1);
		Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), unit2);

		boolean result = a.equals(b);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
				OperationType.COMPARE, q1.toString(), q2.toString(), String.valueOf(result), false, null);

		entity.setUsername(username);

		repository.save(entity);

		return new QuantityMeasurementDTO(OperationType.COMPARE, q1.toString(), q2.toString(),
				String.valueOf(result), false, null);
	}

	@Override
	public QuantityMeasurementDTO convert(QuantityInputDTO input, String username) {

		QuantityDTO q = input.getOperand1();

		IMeasurable sourceUnit = getUnit(q.getType(), q.getUnit());
		IMeasurable targetUnit = getUnit(q.getType(), input.getTargetUnit());

		Quantity<IMeasurable> quantity = new Quantity<>(q.getValue(), sourceUnit);
		Quantity<IMeasurable> result = quantity.convertTo(targetUnit);

		String resultValue = result.toString();

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
				OperationType.CONVERT, q.toString(), input.getTargetUnit(), resultValue, false, null);

		entity.setUsername(username);

		repository.save(entity);

		return new QuantityMeasurementDTO(OperationType.CONVERT, q.toString(), input.getTargetUnit(),
				resultValue, false, null);
	}

	@Override
	public QuantityMeasurementDTO add(QuantityInputDTO input, String username) {

		QuantityDTO q1 = input.getOperand1();
		QuantityDTO q2 = input.getOperand2();

		IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

		Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), unit1);
		Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), unit2);

		Quantity<IMeasurable> result = a.add(b);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
				OperationType.ADD, q1.toString(), q2.toString(), result.toString(), false, null);

		entity.setUsername(username);

		repository.save(entity);

		return new QuantityMeasurementDTO(OperationType.ADD, q1.toString(), q2.toString(),
				result.toString(), false, null);
	}

	@Override
	public QuantityMeasurementDTO subtract(QuantityInputDTO input, String username) {

		QuantityDTO q1 = input.getOperand1();
		QuantityDTO q2 = input.getOperand2();

		IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

		Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), unit1);
		Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), unit2);

		Quantity<IMeasurable> result = a.subtract(b);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
				OperationType.SUBTRACT, q1.toString(), q2.toString(), result.toString(), false, null);

		entity.setUsername(username);

		repository.save(entity);

		return new QuantityMeasurementDTO(OperationType.SUBTRACT, q1.toString(), q2.toString(),
				result.toString(), false, null);
	}

	@Override
	public QuantityMeasurementDTO divide(QuantityInputDTO input, String username) {

		QuantityDTO q1 = input.getOperand1();
		QuantityDTO q2 = input.getOperand2();

		IMeasurable unit1 = getUnit(q1.getType(), q1.getUnit());
		IMeasurable unit2 = getUnit(q2.getType(), q2.getUnit());

		Quantity<IMeasurable> a = new Quantity<>(q1.getValue(), unit1);
		Quantity<IMeasurable> b = new Quantity<>(q2.getValue(), unit2);

		double result = a.divide(b);

		QuantityMeasurementEntity entity = new QuantityMeasurementEntity(
				OperationType.DIVIDE, q1.toString(), q2.toString(), String.valueOf(result), false, null);

		entity.setUsername(username);

		repository.save(entity);

		return new QuantityMeasurementDTO(OperationType.DIVIDE, q1.toString(), q2.toString(),
				String.valueOf(result), false, null);
	}

	@Override
	public List<QuantityMeasurementDTO> getHistoryByOperation(String operation, String username) {

		OperationType operationType;

		try {
			operationType = OperationType.valueOf(operation);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Invalid operation type: " + operation);
		}

		List<QuantityMeasurementEntity> entities =
				repository.findByOperationAndUsername(operationType, username);

		return entities.stream()
				.map(this::convertToDTO)
				.toList();
	}

	private QuantityMeasurementDTO convertToDTO(QuantityMeasurementEntity entity) {

		QuantityMeasurementDTO dto = new QuantityMeasurementDTO();

		dto.setOperation(entity.getOperation());
		dto.setOperand1(entity.getOperand1());
		dto.setOperand2(entity.getOperand2());
		dto.setResult(entity.getResult());
		dto.setError(entity.isError());
		dto.setErrorMessage(entity.getErrorMessage());

		return dto;
	}
}