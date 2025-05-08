package teamkim.stream.domain.logging.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "logging_entity")
public class LoggingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float confidence;

    private String imageUrl;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DirectionType directionType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loggingEntity")
    List<ClassEntity> classEntityList;

    @Builder
    public LoggingEntity(float confidence, String imageUrl, DirectionType directionType) {
        this.confidence = confidence;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.directionType = directionType;
    }
}