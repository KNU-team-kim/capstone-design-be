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
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;
    private final LoggingS3Service loggingS3Service;

    @Transactional
    public LoggingEntity saveLog(ClassType classType, float confidence, String fileName, DirectionType directionType) {

//        // fileName이 없으면 UUID로 자동 생성
//        if (fileName == null || fileName.isEmpty()) {
//            fileName = UUID.randomUUID().toString() + ".jpg";
//        }

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