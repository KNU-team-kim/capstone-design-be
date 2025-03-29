package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveLog(@RequestParam ClassType classType, @RequestParam Direction direction) {
        logService.saveLog(classType, direction);
        return ResponseEntity.ok("Log saved successfully");
    }
}
