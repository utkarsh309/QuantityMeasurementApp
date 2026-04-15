package com.app.qma_service.service;

import java.util.List;

import com.app.qma_service.model.QuantityInputDTO;
import com.app.qma_service.model.QuantityMeasurementDTO;

public interface IQuantityMeasurementService {

    QuantityMeasurementDTO compare(QuantityInputDTO input, String username);

    QuantityMeasurementDTO convert(QuantityInputDTO input, String username);

    QuantityMeasurementDTO add(QuantityInputDTO input, String username);

    QuantityMeasurementDTO subtract(QuantityInputDTO input, String username);

    QuantityMeasurementDTO divide(QuantityInputDTO input, String username);

    List<QuantityMeasurementDTO> getHistoryByOperation(String operation, String username);
}