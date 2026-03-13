CREATE TABLE IF NOT EXISTS quantity_measurement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50),
    operand1 VARCHAR(50),
    operand2 VARCHAR(50),
    result VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);