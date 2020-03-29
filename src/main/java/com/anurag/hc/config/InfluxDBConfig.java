/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class InfluxDBConfig {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public InfluxDB influxDB() {
        Environment environment = applicationContext.getEnvironment();
        String databaseURL = environment.getProperty("influx.db.url");
        return InfluxDBFactory.connect(databaseURL);
    }
}

