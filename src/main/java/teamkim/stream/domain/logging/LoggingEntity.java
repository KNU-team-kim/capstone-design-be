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

    private LocalDateTime createAt;

    @Enumerated(EnumType.STRING)
    private Direction direction;

    public LoggingEntity(ClassType classType, LocalDateTime createAt, Direction direction) {
        this.classType = classType;
        this.createAt = createAt;
        this.direction = direction;
    }
}