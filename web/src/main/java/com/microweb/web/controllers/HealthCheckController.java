package com.microweb.web.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.management.ManagementFactory;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
@RequestMapping(path = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
public class HealthCheckController {

  private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

  private final com.sun.management.OperatingSystemMXBean osBean;
  private final Path diskPath;

  public HealthCheckController(
      @Value("${health.disk.path:/}") String diskPathStr) {
    var baseBean = ManagementFactory.getOperatingSystemMXBean();
    this.osBean = (baseBean instanceof com.sun.management.OperatingSystemMXBean sun)
        ? sun
        : null;
    this.diskPath = Paths.get(diskPathStr);
  }

  @GetMapping
  public ResponseEntity<HealthStatus> health() {
    return ResponseEntity.ok(new HealthStatus(
        "UP",
        LocalDateTime.now().format(ISO_FORMATTER)));
  }
  @GetMapping("/diagnostics")
  public ResponseEntity<Map<String, Object>> diagnostics() {
    return ResponseEntity.ok(Map.of(
        "cpuUsage", getCpuUsage() + "%",
        "memoryUsage", getMemoryUsage() + "%",
        "diskSpace", formatDiskSpace(getDiskSpace()),
        "timestamp", LocalDateTime.now().format(ISO_FORMATTER)
    ));
  }

  private Map<String, String> formatDiskSpace(Map<String, Long> diskSpace) {
    return Map.of(
        "total", formatBytesToGB(diskSpace.get("total")) + " GB",
        "free", formatBytesToGB(diskSpace.get("free")) + " GB",
        "used", formatBytesToGB(diskSpace.get("used")) + " GB"
    );
  }

  private String formatBytesToGB(long bytes) {
    return String.format("%.2f", bytes / (1024.0 * 1024.0 * 1024.0));
  }

  private double getCpuUsage() {
    if (osBean == null) {
      return -1;
    }
    double load = osBean.getCpuLoad();
    return (load < 0) ? -1 : load * 100;
  }

  private double getMemoryUsage() {
    var rt = Runtime.getRuntime();
    double used = rt.totalMemory() - rt.freeMemory();
    return (used / rt.totalMemory()) * 100;
  }

  private Map<String, Long> getDiskSpace() {
    try {
      FileStore store = Files.getFileStore(diskPath);
      long total = store.getTotalSpace();
      long free = store.getUnallocatedSpace();
      return Map.of(
          "total", total,
          "free", free,
          "used", total - free);
    } catch (IOException e) {
      throw new UncheckedIOException("Error reading disk space", e);
    }
  }

  private static record HealthStatus(String status, String timestamp) {
  }

  private static record Diagnostics(
      double cpuUsage,
      double memoryUsage,
      Map<String, Long> diskSpace,
      String timestamp) {
  }
}
