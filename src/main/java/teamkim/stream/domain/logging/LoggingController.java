package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
