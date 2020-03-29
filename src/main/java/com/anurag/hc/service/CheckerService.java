/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.service;


import com.anurag.hc.core.handler.RequestHandler;
import com.anurag.hc.database.dynamodb.AWSDynamoService;
import com.anurag.hc.model.HealthCheckModel;
import com.anurag.hc.model.LatencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckerService {

    @Autowired
    AWSDynamoService awsDynamoService;

    @Autowired
    RequestHandler requestHandler;

    public HealthCheckModel triggerApi(String jobId) {

        HealthCheckModel apiDetails = awsDynamoService.findById(jobId);
        return requestHandler.processApi(apiDetails);
    }

    public Boolean saveData(LatencyData latencyData) {
        return requestHandler.saveLatencyData(latencyData);
    }
}
