/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.core.handler;

import com.anurag.hc.core.builder.ApiBuilder;
import com.anurag.hc.database.dynamodb.AWSDynamoService;
import com.anurag.hc.database.influxdb.InfluxService;
import com.anurag.hc.model.DynamoDBSaveModel;
import com.anurag.hc.model.HealthCheckModel;
import com.anurag.hc.model.InfluxDBSaveModel;
import com.anurag.hc.model.LatencyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestHandlerImpl implements RequestHandler {


    @Autowired
    ApiBuilder apiBuilder;

    @Autowired
    InfluxService influxService;

    @Autowired
    AWSDynamoService awsDynamoService;

    @Override
    public HealthCheckModel processApi(HealthCheckModel apiDetails) {
        HealthCheckModel requestDetails = apiBuilder.setValues(apiDetails);
        return requestDetails;
    }

    @Override
    public Boolean save(LatencyData latencyData) {
        InfluxDBSaveModel influxDBSaveModel = new InfluxDBSaveModel();
        influxDBSaveModel.setApiLatency(latencyData.getApiLatency());
        influxDBSaveModel.setApiName(latencyData.getApiName());
        influxDBSaveModel.setApiStatus(latencyData.getApiStatus());
        return influxService.save(influxDBSaveModel);
    }

    @Override
    public Boolean save(HealthCheckModel healthCheckModel) {
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

    @Override
    public <T> T fetchData(String jobId, T returnType) {
        HealthCheckModel apiModel = awsDynamoService.findById(jobId);
        return (T) apiModel;
    }
}


