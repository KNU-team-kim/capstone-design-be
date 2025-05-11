package teamkim.stream.domain.logging.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamkim.stream.domain.logging.service.LoggingS3Service;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
@Tag(name = "S3 Presigned URL API", description = "AWS S3 Presigned URL을 생성하는 API")
public class LoggingS3Controller {

    private final LoggingS3Service loggingS3Service;

    @Operation(summary = "업로드 Presigned URL 생성", description = "파일을 S3에 업로드할 때 사용할 Presigned URL을 생성한다.")
    @GetMapping("/presigned/upload")
    public ResponseEntity<String> generateUploadUrl(
            @Parameter(description = "S3에 저장할 파일명 (예: image.jpg)", required = true)
            @RequestParam String fileName) {
        String presignedUrl = loggingS3Service.getUploadPresignedUrl(fileName);
        return ResponseEntity.ok(presignedUrl);
    }

    /*
    @Operation(summary = "다운로드 Presigned URL 생성", description = "S3에 저장된 파일을 다운로드할 때 사용할 Presigned URL을 생성한다.")
    @GetMapping("/presigned/download")
    public ResponseEntity<String> generateDownloadUrl(
            @Parameter(description = "다운로드할 파일명 (예: image.jpg)", required = true)
            @RequestParam String fileName) {
        String fileUrl = loggingS3Service.getS3FileUrl(fileName);
        return ResponseEntity.ok(fileUrl);
    }
    */
}