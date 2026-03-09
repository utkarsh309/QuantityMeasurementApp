package com.quantitymeasurementapp;

public class Weight {

	private final double value;
	private final WeightUnit unit;

	public Weight(double value, WeightUnit unit) {

		if (!Double.isFinite(value)) {
			throw new IllegalArgumentException("Value must be finite.");
		}

		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null.");
		}

		this.value = value;
		this.unit = unit;
	}

	private double convertToBaseUnit() {
		return unit.convertToBaseUnit(value);
	}

	public Weight convertTo(WeightUnit targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null.");
		}

		double baseValue = convertToBaseUnit();

		double converted = targetUnit.convertFromBaseUnit(baseValue);

		double rounded = Math.round(converted * 100.0) / 100.0;

		return new Weight(rounded, targetUnit);
	}

	public Weight add(Weight other) {

		if (other == null) {
			throw new IllegalArgumentException("Second operand cannot be null.");
		}

		double sumBase = this.convertToBaseUnit() + other.convertToBaseUnit();

		double result = unit.convertFromBaseUnit(sumBase);

		return new Weight(result, unit);
	}

	public Weight add(Weight other, WeightUnit targetUnit) {

		if (other == null || targetUnit == null) {
			throw new IllegalArgumentException("Operands cannot be null.");
		}

		double sumBase = this.convertToBaseUnit() + other.convertToBaseUnit();

		double result = targetUnit.convertFromBaseUnit(sumBase);

		return new Weight(result, targetUnit);
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Weight other = (Weight) obj;

		return Double.compare(this.convertToBaseUnit(), other.convertToBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(convertToBaseUnit());
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}