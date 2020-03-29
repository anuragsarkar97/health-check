package com.anurag.hc.core.builder;

import com.anurag.hc.model.HealthCheckModel;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@Component
public class ApiBuilder implements Serializable {

    public HealthCheckModel setValues(HealthCheckModel apiDetails) {
        Map variables = apiDetails.getVariables();
        variables.forEach((k, v) -> {
                    apiDetails.setHostUrl(apiDetails.getHostUrl().replace("${" + k + "}", v.toString()));
                    apiDetails.setParams(apiDetails.getParams().replace("${" + k + "}", v.toString()));
                    apiDetails.setBody(apiDetails.getBody().replace("${" + k + "}", v.toString()));
                    apiDetails.setHeaders(apiDetails.getHeaders().replace("${" + k + "}", v.toString()));
                }
        );
        return apiDetails;
    }
}
