package teamkim.stream.domain.logging;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "logging_entity")
public class LoggingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClassType classType;

    private float confidence;

    private String imageUrl;

    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private DirectionType directionType;

    public LoggingEntity(ClassType classType, float confidence, String imageUrl, LocalDateTime createdAt, DirectionType directionType) {
        this.classType = classType;
        this.confidence = confidence;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;;
        this.directionType = directionType;
    }
}