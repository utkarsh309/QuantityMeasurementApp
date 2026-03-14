package com.quantitymeasurementapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.exception.DatabaseException;
import com.quantitymeasurementapp.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {
	
	private final ConnectionPool pool = ConnectionPool.getInstance();
	public QuantityMeasurementDatabaseRepository() {
	    createTableIfNotExists();
	}

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement(operation, operand1, operand2, result) VALUES (?, ?, ?, ?)";

    private static final String SELECT_SQL =
            "SELECT operation, operand1, operand2, result FROM quantity_measurement";

    
    private void createTableIfNotExists() {

        String sql = """
            CREATE TABLE IF NOT EXISTS quantity_measurement (
                id INT AUTO_INCREMENT PRIMARY KEY,
                operation VARCHAR(50),
                operand1 VARCHAR(50),
                operand2 VARCHAR(50),
                result VARCHAR(50)
            )
            """;

        try (Connection connection = pool.getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            throw DatabaseException.queryFailed(sql, e);
        }
    }

    @Override
    public void save(QuantityMeasurementEntity entity) {

        Connection connection = null;

        try {

            connection = pool.getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(INSERT_SQL);

            statement.setString(1, entity.getOperation());
            statement.setString(2, entity.getOperand1());
            statement.setString(3, entity.getOperand2());
            statement.setString(4, entity.getResult());

            statement.executeUpdate();

        } catch (SQLException e) {

            throw DatabaseException.queryFailed(INSERT_SQL, e);

        } finally {

            if (connection != null)
                pool.releaseConnection(connection);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllOperations() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        Connection connection = null;

        try {

            connection = pool.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(SELECT_SQL);

            while (rs.next()) {

                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getString("operand1"),
                        rs.getString("operand2"),
                        rs.getString("result")
                ));
            }

        } catch (SQLException e) {

            throw DatabaseException.queryFailed(SELECT_SQL, e);

        } finally {

            if (connection != null)
                pool.releaseConnection(connection);
        }

        return list;
    }
}