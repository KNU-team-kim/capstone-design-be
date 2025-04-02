package teamkim.stream.domain.logging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.service.LoggingService;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {

    private final LoggingService loggingService;

    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Operation(summary = "Save a log", description = "Save a new log entry with ClassType, Direction, Confidence, and Image URL")
    @PostMapping("/save")
    public ResponseEntity<String> saveLog(
            @RequestParam ClassType classType,
            @RequestParam float confidence,
            @RequestParam String imageUrl,
            @RequestParam DirectionType directionType
    ) {
        loggingService.saveLog(classType, confidence, imageUrl, directionType);
        return ResponseEntity.ok("Log saved successfully");
    }

    @Operation(summary = "Get all logs", description = "Retrieve all the log entries")
    @GetMapping("/all")
    public ResponseEntity<List<LoggingEntity>> getAllLogs() {
        List<LoggingEntity> logs = loggingService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @Operation(summary = "Get logs by ClassType", description = "Retrieve log entries by ClassType")
    @GetMapping("/classType/{classType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByClassType(
            @Parameter(description = "ClassType to filter the logs") @PathVariable ClassType classType) {
        List<LoggingEntity> logs = loggingService.getLogsByClassType(classType);
        return ResponseEntity.ok(logs);
    }

    @Operation(summary = "Get logs by Direction", description = "Retrieve log entries by DirectionType")
    @GetMapping("/directionType/{directionType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByDirection(
            @Parameter(description = "DirectionType to filter the logs") @PathVariable DirectionType directionType) {
        List<LoggingEntity> logs = loggingService.getLogsByDirectionType(directionType);
        return ResponseEntity.ok(logs);
    }
}