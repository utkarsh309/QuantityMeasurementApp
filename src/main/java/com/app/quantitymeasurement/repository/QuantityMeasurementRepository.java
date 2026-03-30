package com.app.quantitymeasurement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.quantitymeasurement.model.QuantityMeasurementEntity;
import com.app.quantitymeasurement.enums.OperationType;

/**
 * Spring Data JPA repository for QuantityMeasurementEntity.
 *
 * Provides CRUD operations automatically without writing SQL.
 */
@Repository
public interface QuantityMeasurementRepository
        extends JpaRepository<QuantityMeasurementEntity, Long> {

    
    List<QuantityMeasurementEntity> findByOperation(OperationType operation);

    
    List<QuantityMeasurementEntity> findByErrorTrue();

    
    long countByOperationAndErrorFalse(OperationType operation);

}