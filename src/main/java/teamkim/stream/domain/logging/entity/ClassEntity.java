package teamkim.stream.domain.logging.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import teamkim.stream.domain.logging.enums.ClassType;

@Entity(name = "class_entity")
@Getter
@NoArgsConstructor
public class ClassEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClassType classType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id")
    private LoggingEntity loggingEntity;

    @Builder
    ClassEntity(LoggingEntity loggingEntity, ClassType classType) {
        this.loggingEntity = loggingEntity;
        this.classType = classType;
    }
}
