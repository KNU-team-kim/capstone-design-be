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
    private ClassType classType; // YOLO 클래스 타입

    private float confidence; // YOLO 탐지 정확도(%)

    private String imageUrl; //탐지된 이미지 (S3 URL)

    private LocalDateTime createdAt; // 탐지된 시간

    @Enumerated(EnumType.STRING)
    private Direction direction; // 카메라 방향

    public LoggingEntity(ClassType classType, float confidence, String imageUrl, LocalDateTime createdAt, Direction direction) {
        this.classType = classType;
        this.confidence = confidence;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.direction = direction;
    }
}