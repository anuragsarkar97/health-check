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
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class InfluxServiceImpl implements InfluxService {

    @Autowired
    private InfluxDBConfig influxDBConfig;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public Boolean save(InfluxDBSaveModel influxDBSaveModel) {
        Environment environment = applicationContext.getEnvironment();
        Point point = Point.measurement(influxDBSaveModel.getApiName())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("name", influxDBSaveModel.getApiName())
                .addField("latency", influxDBSaveModel.getApiLatency())
                .addField("status", influxDBSaveModel.getApiStatus())
                .build();
        try {
            influxDBConfig.influxDB().write(environment.getProperty("influx.db.name"),
                    "autogen",
                    point);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
