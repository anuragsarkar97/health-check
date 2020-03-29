/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.database.dynamodb;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.anurag.hc.config.DynamoDBConfig;
import com.anurag.hc.model.DynamoDBSaveModel;
import com.anurag.hc.model.HealthCheckModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.anurag.hc.constants.DynamoDBConstant.*;

@Component
public class AWSDynamoServiceImpl implements AWSDynamoService {

    @Autowired
    private DynamoDBConfig dynamoDBConfig;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${dynamo.db.table}")
    private String tableName;


    @Override
    public Boolean save(DynamoDBSaveModel saveObject) {
        HashMap<String, AttributeValue> values = new HashMap<>();
        values.put(NAME, new AttributeValue(saveObject.getName()));
        values.put(JOB_ID, new AttributeValue(saveObject.getJobId()));
        values.put(BODY, new AttributeValue(saveObject.getBody()));
        values.put(CRON, new AttributeValue(saveObject.getCron()));
        values.put(HEADERS, new AttributeValue(saveObject.getHeaders()));
        values.put(VARIABLE, new AttributeValue(saveObject.getVariable()));
        values.put(HOST_URL, new AttributeValue(saveObject.getHostURL()));
        values.put(PARAMS, new AttributeValue(saveObject.getParam()));
        values.put(REQUEST_TYPE, new AttributeValue(saveObject.getRequestType().toString()));
        try {
            dynamoDBConfig.amazonDynamoDB().putItem(tableName, values);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public HealthCheckModel findById(String id) {
        HashMap<String, AttributeValue> keys = new HashMap<>();
        keys.put(JOB_ID, new AttributeValue(id));
        GetItemRequest request = null;
        request = new GetItemRequest()
                .withKey(keys)
                .withTableName(tableName);
        Map<String, AttributeValue> returnedItem = dynamoDBConfig.amazonDynamoDB()
                .getItem(request)
                .getItem();
        Map<String, String> response = new HashMap<>();
        returnedItem.forEach((key, value) -> {
            response.put(key, value.getS());
        });
        HealthCheckModel healthCheckModel = objectMapper.convertValue(response, HealthCheckModel.class);
        return healthCheckModel;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }
}

