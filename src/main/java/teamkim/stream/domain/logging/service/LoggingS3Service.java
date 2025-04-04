package teamkim.stream.domain.logging.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;
import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoggingS3Service {
    private final AmazonS3 amazonS3;

    private final String bucketName = "presigned-url-bucket-teamkim";

    // Presigned URL 생성 메서드 (업로드용)
    public String getUploadPresignedUrl(String fileName) {
        Date expiration = new Date();
        expiration.setTime(expiration.getTime() + 600 * 1000); // 10분 후 만료

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(expiration);

        return amazonS3.generatePresignedUrl(generatePresignedUrlRequest).toString();
    }

    // 업로드된 S3 이미지의 최종 URL 반환 메서드
    public String getS3FileUrl(String fileName) {
        return "https://" + bucketName + ".s3.ap-northeast-2.amazonaws.com/" + fileName;
    }
}
