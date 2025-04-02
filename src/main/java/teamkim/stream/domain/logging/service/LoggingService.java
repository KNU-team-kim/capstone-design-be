package teamkim.stream.domain.logging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.repository.LoggingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;
    private final LoggingS3Service loggingS3Service;

    @Transactional
    public LoggingEntity saveLog(ClassType classType, float confidence, String fileName, DirectionType directionType) {

        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("fileName must not be null or empty");
        }

        // Presigned URL 생성
        String imageUrl = loggingS3Service.getS3FileUrl(fileName);

        // LoggingEntity 생성 및 저장
        LoggingEntity log = new LoggingEntity(
                classType,
                confidence,
                imageUrl,
                fileName,
                LocalDateTime.now(),
                directionType
        );

        return loggingRepository.save(log);
    }

    // 전체 로그 조회
    public List<LoggingEntity> getAllLogs() {
        return loggingRepository.findAll();
    }

    // 특정 classType 로그 조회
    public List<LoggingEntity> getLogsByClassType(ClassType classType) {
        return loggingRepository.findByClassType(classType);
    }

    // 특정 directionType 로그 조회
    public List<LoggingEntity> getLogsByDirectionType(DirectionType directionType) {
        return loggingRepository.findByDirectionType(directionType);
    }
}