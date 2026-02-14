package com.project.ecommerce.controller;

import com.project.ecommerce.service.HealthService;
import com.project.ecommerce.service.HealthServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HealthController {

    //Constructor Injection
    private final HealthService healthService;

    public HealthController(HealthService healthService) {

        this.healthService = healthService;
    }

    // checks if db is up and running
    @GetMapping("/db-check")
    public ResponseEntity<?> checkDBStatus() {

        try{
            List<String> tables = healthService.checkDatabase();

            Map<String, Object> response = new LinkedHashMap<>();
            response.put("status", "SUCCESS");
            response.put("message","Database Connection Succeeded");
            response.put("tables",tables);

            return ResponseEntity.ok(response);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Database connection failed");
        }
    }
}
