package com.anurag.hc.api;

import com.anurag.hc.model.HealthCheckModel;
import com.anurag.hc.model.LatencyData;
import com.anurag.hc.model.Status;
import com.anurag.hc.service.CheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    CheckerService checkerService;

    @GetMapping("/ping")
    public String getStatus() {
        System.out.println("received request");
        return "OK";
    }

    @GetMapping("/trigger")
    public HealthCheckModel triggerJob(@RequestParam("job_id") String jobId) {
        System.out.println("received request trigger");
        try {
            return checkerService.getDynamoDbData(jobId);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/save_data")
    public Boolean saveLatencyData(@RequestBody LatencyData latencyData) {
        return checkerService.saveDataInfluxDb(latencyData);
    }

    @PostMapping("/add/single_step")
    public Status addHealthCheck(@RequestBody HealthCheckModel healthCheckModel) {
        Boolean status = checkerService.saveToDynamoDb(healthCheckModel);
        if(status) {
            return Status.SUCCESS;
        } else {
            return Status.FAILED;
        }
    }
}
