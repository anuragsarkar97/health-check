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
import com.anurag.hc.model.DynamoDBSaveModel;
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

    public HealthCheckModel getDynamoDbData(String jobId) {
        HealthCheckModel apiDetails = awsDynamoService.findById(jobId);
        return requestHandler.processApi(apiDetails);
    }

    public Boolean saveDataInfluxDb(LatencyData latencyData) {
        return requestHandler.saveLatencyData(latencyData);
    }

    public Boolean saveToDynamoDb(HealthCheckModel healthCheckModel) {
        DynamoDBSaveModel dynamoDBSaveModel = new DynamoDBSaveModel();
        dynamoDBSaveModel.setBody(healthCheckModel.getBody());
        dynamoDBSaveModel.setCron(healthCheckModel.getCron());
        dynamoDBSaveModel.setHeaders(healthCheckModel.getHeaders());
        dynamoDBSaveModel.setHostURL(healthCheckModel.getHostUrl());
        dynamoDBSaveModel.setJobId(healthCheckModel.getJobId());
        dynamoDBSaveModel.setName(healthCheckModel.getName());
        dynamoDBSaveModel.setParam(healthCheckModel.getParams());
        dynamoDBSaveModel.setRequestType(healthCheckModel.getRequestType());
        dynamoDBSaveModel.setVariable(healthCheckModel.getVariables());
        try {
            awsDynamoService.save(dynamoDBSaveModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



}
