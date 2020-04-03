package com.anurag.hc.api;

import com.anurag.hc.constants.RestEndpoints;
import com.anurag.hc.core.handler.RequestHandler;
import com.anurag.hc.model.HealthCheckModel;
import com.anurag.hc.model.LatencyData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class Controller {


    @Autowired
    RequestHandler requestHandler;

    @GetMapping(RestEndpoints.PING)
    public ResponseEntity<Void> getStatus() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(RestEndpoints.GETINFO)
    public ResponseEntity<HealthCheckModel> triggerJob(@RequestParam("job_id") String jobId) {
        return new ResponseEntity<>(requestHandler.fetchData(jobId, new HealthCheckModel()), HttpStatus.OK);
    }

    @PostMapping(RestEndpoints.SAVE)
    public Boolean saveLatencyData(@RequestBody LatencyData latencyData) {
        return requestHandler.save(latencyData);
    }

    @PostMapping(RestEndpoints.ADD)
    public Boolean addHealthCheck(@RequestBody HealthCheckModel healthCheckModel) {
        return requestHandler.save(healthCheckModel);
    }
}
