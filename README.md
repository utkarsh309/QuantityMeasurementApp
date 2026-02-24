# Quantity Measurement Application

Trainee – Utkarsh

## 📌 Project Overview

A scalable **Quantity Measurement System** implemented using clean Object-Oriented Programming (OOP) principles.  

The system evolved progressively from basic equality checks to a fully extensible unit conversion framework, supporting multiple units, cross-unit comparisons, and a structured conversion API.

---

## 🏗 Architecture Hierarchy

QuantityMeasurementApp  
│  
├── LengthUnit (Enum)  
│ ├── FEET  
│ ├── INCHES  
│ ├── YARDS  
│ └── CENTIMETERS  
│  
├── Length (Immutable Value Object)  
│ ├── value  
│ ├── unit  
│ ├── convertToBaseUnit()  
│ ├── convertTo()  
│ ├── equals()  
│ ├── hashCode()  
│ └── toString()  
│  
└── Static APIs  
├── convert()  
├── demonstrateLengthEquality()  
└── demonstrateLengthConversion()  

---

## 📈 Use Case Evolution (UC1 → UC5)

### 🟢 UC1 – Basic Equality
- Implemented equality comparison for **Feet**
- Overrode `equals()` method
- Ensured null and type safety

### 🟢 UC2 – Multiple Unit Support
- Added **Inches** class
- Separate equality logic for each unit
- Highlighted code duplication issue

### 🟢 UC3 – Generic Design (DRY Principle Applied)
- Introduced `LengthUnit` enum
- Created generic `Length` class
- Enabled cross-unit equality (e.g., 1 ft == 12 inches)

### 🟢 UC4 – Extended Unit Support
- Added **Yards** and **Centimeters**
- No modification required in core logic
- Demonstrated Open-Closed Principle

### 🟢 UC5 – Unit-to-Unit Conversion API
- Added static `convert()` method
- Implemented instance `convertTo()` method
- Added method overloading
- Ensured `equals()` and `hashCode()` consistency
- Implemented rounding to 2 decimal places
