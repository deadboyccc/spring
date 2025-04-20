package com.microweb.web.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthCheckController {

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, String>> health() {
    Map<String, String> healthStatus = Map.of(
        "status", "UP",
        "timestamp", java.time.LocalDateTime.now().toString());
    return ResponseEntity.ok(healthStatus);
  }
}