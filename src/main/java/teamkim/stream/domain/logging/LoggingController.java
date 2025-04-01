package teamkim.stream.domain.logging;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
            @RequestParam Direction direction
    ) {
        loggingService.saveLog(classType, confidence, imageUrl, direction);
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

    @Operation(summary = "Get logs by Direction", description = "Retrieve log entries by Direction")
    @GetMapping("/direction/{direction}")
    public ResponseEntity<List<LoggingEntity>> getLogsByDirection(
            @Parameter(description = "Direction to filter the logs") @PathVariable Direction direction) {
        List<LoggingEntity> logs = loggingService.getLogsByDirection(direction);
        return ResponseEntity.ok(logs);
    }
}
