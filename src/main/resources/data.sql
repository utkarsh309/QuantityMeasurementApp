INSERT INTO quantity_measurement (operation, operand1, operand2, result, error, error_message)
VALUES ('ADD', '2 FEET', '24 INCHES', '4 FEET', false, NULL);

INSERT INTO quantity_measurement (operation, operand1, operand2, result, error, error_message)
VALUES ('COMPARE', '1 KG', '1000 GRAM', 'true', false, NULL);

INSERT INTO quantity_measurement (operation, operand1, operand2, result, error, error_message)
VALUES ('CONVERT', '1 GALLON', 'LITER', '3.785 LITER', false, NULL);

INSERT INTO quantity_measurement (operation, operand1, operand2, result, error, error_message)
VALUES ('DIVIDE', '10 FEET', '2 FEET', '5', false, NULL);