/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.core.handler;

import com.anurag.hc.model.HealthCheckModel;
import com.anurag.hc.model.LatencyData;

public interface RequestHandler {

    public HealthCheckModel processApi(HealthCheckModel apiDetails);

    public Boolean saveLatencyData(LatencyData latencyData);


}

