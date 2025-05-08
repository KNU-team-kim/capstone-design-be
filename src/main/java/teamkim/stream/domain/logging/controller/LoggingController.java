package teamkim.stream.domain.logging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamkim.stream.domain.logging.dto.LogPageResponseDto;
import teamkim.stream.domain.logging.dto.LogRequestDto;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.service.LoggingService;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Tag(name = "Logging API", description = "로그 관련 API")
public class LoggingController {

    private final LoggingService loggingService;

    // 로그 저장 API (S3 URL 포함)
    @Operation(summary = "로그 저장", description = "객체 탐지 정보를 저장한다.")
    @PostMapping("/save")
    public ResponseEntity<String> saveLog(@RequestBody LogRequestDto logRequestDto) {
        loggingService.saveLog(
                logRequestDto.getClassTypes(),
                logRequestDto.getConfidence(),
                logRequestDto.getFileName(),
                logRequestDto.getDirectionType()
        );
        return ResponseEntity.ok("성공");
    }

    /*
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
    */

    @GetMapping
    @Operation(
            summary = "로그 리스트 조건 조회 API",
            description = "원하는 조건으로 필터링하여 로그의 리스트를 조회할 수 있다. classType과 directionType은 optional",
            responses = {
                    @ApiResponse(responseCode = "200", description = "성공", content = @Content())
            }
    )
    public ResponseEntity<LogPageResponseDto> getLogsByOptions(
            @RequestParam(required = false) ClassType classType,
            @RequestParam(required = false) DirectionType directionType,
            @RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return ResponseEntity.ok(loggingService.getLogsByOptions(classType, directionType, pageRequest));
    }
}