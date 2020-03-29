/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.database.influxdb;

import com.anurag.hc.config.InfluxDBConfig;
import com.anurag.hc.model.InfluxDBSaveModel;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

public class InfluxServiceImpl implements InfluxService {

    @Autowired
    private InfluxDBConfig influxDBConfig;

    @Override
    public Mono<Boolean> save(InfluxDBSaveModel influxDBSaveModel) {
        Point point = Point.measurement("table_name")
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("name", influxDBSaveModel.getApiName())
                .addField("latency", influxDBSaveModel.getApiLatency())
                .addField("status", influxDBSaveModel.getApiStatus())
                .build();
        influxDBConfig.influxDB().write(point);
        return null;
    }
}
