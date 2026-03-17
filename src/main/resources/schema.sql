CREATE TABLE IF NOT EXISTS quantity_measurement (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    operand1 VARCHAR(100),
    operand2 VARCHAR(100),
    result VARCHAR(100),
    error BOOLEAN,
    error_message VARCHAR(255)
);