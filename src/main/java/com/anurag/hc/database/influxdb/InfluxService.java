/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.database.influxdb;

import com.anurag.hc.model.InfluxDBSaveModel;

public interface InfluxService {

    Boolean save(InfluxDBSaveModel healthCheckData);

//    <T> T query(T queryString);

}

