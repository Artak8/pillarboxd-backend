package com.artak.pillarboxd.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvironmentController {
    //Retrieve the application name and allowed origins
    @Value("${spring.application.name}")
    private String appName;

    @Value("${cors.allowed-origins}")
    private String corsOrigins;

    /*
        Create two test pages
        1. For checking the environment the app is running in
        2. Get a heartbeat from the backend
    */
    public String checkEnv() {
        return "App: " + appName + ", CORS: " + corsOrigins;
    }

    @GetMapping("/env")
    public String env() {
        return checkEnv();
    }

    @GetMapping("/")
    public String root() {
        return checkEnv();
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("alive");
    }
}
