package teamkim.stream.domain.logging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamkim.stream.domain.logging.dto.LogRequestDto;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.service.LoggingService;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LoggingController {

    private final LoggingService loggingService;

    // 로그 저장 API (S3 URL 포함)
    @PostMapping("/save")
    public ResponseEntity<LoggingEntity> saveLog(@RequestBody LogRequestDto logRequestDto) {
        LoggingEntity savedLog = loggingService.saveLog(
                logRequestDto.getClassType(),
                logRequestDto.getConfidence(),
                logRequestDto.getFileName(),
                logRequestDto.getDirectionType()
        );
        return ResponseEntity.ok(savedLog);
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoggingEntity>> getAllLogs() {
        return ResponseEntity.ok(loggingService.getAllLogs());
    }

    @GetMapping("/classType/{classType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByClassType(@PathVariable ClassType classType) {
        return ResponseEntity.ok(loggingService.getLogsByClassType(classType));
    }

    @GetMapping("/direction/{directionType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByDirectionType(@PathVariable DirectionType directionType) {
        return ResponseEntity.ok(loggingService.getLogsByDirectionType(directionType));
    }
}