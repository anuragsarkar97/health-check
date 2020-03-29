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
import com.anurag.hc.database.influxdb.InfluxService;
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

    @Override
    public HealthCheckModel processApi(HealthCheckModel apiDetails) {
        HealthCheckModel requestDetails = apiBuilder.setValues(apiDetails);
        return requestDetails;
    }

    @Override
    public Boolean saveLatencyData(LatencyData latencyData) {
        InfluxDBSaveModel influxDBSaveModel = new InfluxDBSaveModel();
        influxDBSaveModel.setApiLatency(latencyData.getApiLatency());
        influxDBSaveModel.setApiName(latencyData.getApiName());
        influxDBSaveModel.setApiStatus(latencyData.getApiStatus());
        return influxService.save(influxDBSaveModel);
    }
}


