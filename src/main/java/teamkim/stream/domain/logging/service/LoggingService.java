package teamkim.stream.domain.logging.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import teamkim.stream.domain.logging.dto.LogPageResponseDto;
import teamkim.stream.domain.logging.dto.LogResponseDto;
import teamkim.stream.domain.logging.entity.ClassEntity;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.repository.ClassRepository;
import teamkim.stream.domain.logging.repository.LoggingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoggingService {

    private final LoggingRepository loggingRepository;
    private final ClassRepository classRepository;
    private final LoggingS3Service loggingS3Service;

    @Transactional
    public void saveLog(List<ClassType> classTypes, float confidence, String fileName, DirectionType directionType) {

        // Presigned URL 생성
        String imageUrl = loggingS3Service.getS3FileUrl(fileName);

        // LoggingEntity 생성
        LoggingEntity log = LoggingEntity.builder()
                .confidence(confidence)
                .directionType(directionType)
                .imageUrl(imageUrl)
                .build();

        loggingRepository.save(log);

        // ClassEntity 생성
        List<ClassEntity> classes = classTypes.stream().map(classType -> ClassEntity.builder()
                .loggingEntity(log)
                .classType(classType)
                .build()
        ).toList();

        classRepository.saveAll(classes);
    }

    public LogPageResponseDto getLogsByOptions(ClassType classType, DirectionType directionType, Pageable pageable) {
        Page<Long> pageIds = loggingRepository.findIdsByOptionsWithPaging(pageable, classType, directionType);
        List<LogResponseDto> logs = loggingRepository.findLogDetailByIdsFetch(pageIds.getContent()).stream().map(LogResponseDto::from).toList();

        return LogPageResponseDto.builder()
                .totalPage(pageIds.getTotalPages())
                .logs(logs)
                .build();
    }

    /*
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
    */
}