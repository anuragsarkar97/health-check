/*
 * Copyright (c) 2020.
 * Aurthor : Anurag Sarkar
 * Please raise an Issue before creating a pull request.
 * Refer HELP for more details.
 *
 *
 */

package com.anurag.hc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DynamoDBSaveModel {

    @JsonProperty("job_id")
    private String jobId;
    @JsonProperty("body")
    private String body;
    @JsonProperty("cron")
    private String cron;
    @JsonProperty("headers")
    private String headers;
    @JsonProperty("host_url")
    private String hostURL;
    @JsonProperty("name")
    private String name;
    @JsonProperty("param")
    private String param;
    @JsonProperty("request_type")
    private RequestType requestType;
    @JsonProperty("variable")
    private String variable;

}
