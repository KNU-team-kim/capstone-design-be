package teamkim.stream.domain.logging.dto;

import lombok.Builder;
import lombok.Getter;
import teamkim.stream.domain.logging.entity.ClassEntity;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class LogResponseDto {
    private Long id;
    private String imageUrl;
    private DirectionType direction;
    private List<ClassType> classes;
    private LocalDateTime createdAt;
    private Float confidence;

    public static LogResponseDto from(LoggingEntity loggingEntity) {
        return LogResponseDto.builder()
                .id(loggingEntity.getId())
                .imageUrl(loggingEntity.getImageUrl())
                .direction(loggingEntity.getDirectionType())
                .classes(loggingEntity.getClassEntityList().stream().map(ClassEntity::getClassType).toList())
                .createdAt(loggingEntity.getCreatedAt())
                .confidence(loggingEntity.getConfidence())
                .build();
    }
}
