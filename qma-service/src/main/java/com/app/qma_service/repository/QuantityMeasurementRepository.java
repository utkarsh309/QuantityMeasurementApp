package com.app.qma_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.qma_service.enums.OperationType;
import com.app.qma_service.model.QuantityMeasurementEntity;



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


	List<QuantityMeasurementEntity> findByOperationAndUsername(OperationType operationType, String username);
    
    

}