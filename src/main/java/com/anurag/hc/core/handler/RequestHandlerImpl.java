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
import com.anurag.hc.model.HealthCheckModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestHandlerImpl implements RequestHandler {


    @Autowired
    ApiBuilder apiBuilder;

    @Override
    public HealthCheckModel processApi(HealthCheckModel apiDetails) {
        HealthCheckModel requestDetails = apiBuilder.setValues(apiDetails);
        return requestDetails;
    }
}


