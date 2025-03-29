package teamkim.stream.domain.logging;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoggingRepository extends JpaRepository<LoggingEntity, Long> {

    List<LoggingEntity> findByClassType(ClassType classType);
}