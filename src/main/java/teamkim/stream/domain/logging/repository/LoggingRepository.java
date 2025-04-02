package teamkim.stream.domain.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;

import java.util.List;

public interface LoggingRepository extends JpaRepository<LoggingEntity, Long> {

    List<LoggingEntity> findByClassType(ClassType classType);

    List<LoggingEntity> findByDirectionType(DirectionType directionType);
}