/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.database.dynamodb;

import com.anurag.hc.model.DynamoDBSaveModel;
import com.anurag.hc.model.HealthCheckModel;
import org.springframework.stereotype.Repository;

@Repository
public interface AWSDynamoService {

    Boolean save(DynamoDBSaveModel healthCheckData);

    HealthCheckModel findById(String id);

    Boolean delete(String id);

}

