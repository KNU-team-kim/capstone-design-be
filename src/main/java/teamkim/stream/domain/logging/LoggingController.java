package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LoggingController {

    private final LoggingService loggingService;

    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLog(@RequestParam ClassType classType, @RequestParam Direction direction) {
        loggingService.saveLog(classType, direction);
        return ResponseEntity.ok("Log saved successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoggingEntity>> getAllLogs() {
        List<LoggingEntity> logs = loggingService.getAllLogs();
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/classType/{classType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByClassType(@PathVariable ClassType classType) {
        List<LoggingEntity> logs = loggingService.getLogsByClassType(classType);
        return ResponseEntity.ok(logs);
    }

    @GetMapping("/direction/{direction}")
    public ResponseEntity<List<LoggingEntity>> getLogsByDirection(@PathVariable Direction direction) {
        List<LoggingEntity> logs = loggingService.getLogsByDirection(direction);
        return ResponseEntity.ok(logs);
    }
}
