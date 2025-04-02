package teamkim.stream.domain.logging.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teamkim.stream.domain.logging.service.LoggingS3Service;

import java.net.URL;

@RestController
@RequestMapping("/api/s3")
@RequiredArgsConstructor
public class LoggingS3Controller {

    private final LoggingS3Service loggingS3Service;

    // 업로드 Presigned URL 생성 API
    @GetMapping("/presigned/upload")
    public ResponseEntity<String> generateUploadUrl(@RequestParam String fileName) {
        URL presignedUrl = loggingS3Service.generatePresignedUrlForUpload(fileName);
        return ResponseEntity.ok(presignedUrl.toString());
    }

    // 다운로드 Presigned URL 생성 API
    @GetMapping("/presigned/download")
    public ResponseEntity<String> generateDownloadUrl(@RequestParam String fileName) {
        URL presignedUrl = loggingS3Service.generatePresignedUrlForDownload(fileName);
        return ResponseEntity.ok(presignedUrl.toString());
    }
}