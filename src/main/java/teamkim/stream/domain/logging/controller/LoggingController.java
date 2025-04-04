package teamkim.stream.domain.logging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Logging API", description = "로그 관련 API")
public class LoggingController {

    private final LoggingService loggingService;

    // 로그 저장 API (S3 URL 포함)
    @Operation(summary = "로그 저장", description = "객체 탐지 정보를 저장한다.")
    @PostMapping("/save")
    public ResponseEntity<LoggingEntity> saveLog(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그 저장 요청 데이터")
            @RequestBody LogRequestDto logRequestDto) {
        LoggingEntity savedLog = loggingService.saveLog(
                logRequestDto.getClassType(),
                logRequestDto.getConfidence(),
                logRequestDto.getFileName(),
                logRequestDto.getDirectionType()
        );
        return ResponseEntity.ok(savedLog);
    }

    @Operation(summary = "전체 로그 조회", description = "저장된 모든 로그를 조회한다.")
    @GetMapping("/all")
    public ResponseEntity<List<LoggingEntity>> getAllLogs() {
        return ResponseEntity.ok(loggingService.getAllLogs());
    }

    @Operation(summary = "특정 클래스 타입 로그 조회", description = "지정한 객체 유형(classType)에 해당하는 로그를 조회한다.")
    @GetMapping("/classType/{classType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByClassType(
            @Parameter(description = "객체 유형 (예: PAPER_BOX, TRAFFIC_SIGN)")
            @PathVariable ClassType classType) {
        return ResponseEntity.ok(loggingService.getLogsByClassType(classType));
    }

    @Operation(summary = "특정 방향 로그 조회", description = "지정한 방향(directionType)에 해당하는 로그를 조회한다.")
    @GetMapping("/direction/{directionType}")
    public ResponseEntity<List<LoggingEntity>> getLogsByDirectionType(
            @Parameter(description = "탐지 방향 (예: FRONT, BACK, LEFT, RIGHT)")
            @PathVariable DirectionType directionType) {
        return ResponseEntity.ok(loggingService.getLogsByDirectionType(directionType));
    }
}