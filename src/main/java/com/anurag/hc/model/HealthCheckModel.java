/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HealthCheckModel {

    @JsonProperty("job_id")
    private String jobId;
    private String name;
    @JsonProperty("request_type")
    private RequestType requestType;
    @JsonProperty("host_url")
    private String hostUrl;
    private String headers;
    @JsonProperty("variable")
    private Map<String, String> variables;
    @JsonProperty("param")
    private String params;
    private String cron;
    private String body;

    public void setVariables(String jsonString) {
        Gson gson = new Gson();
        this.variables = gson.fromJson(jsonString, Map.class);
    }
}
