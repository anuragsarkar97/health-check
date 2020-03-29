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

public interface RequestHandler {

    public HealthCheckModel processApi(HealthCheckModel apiDetails);


}

