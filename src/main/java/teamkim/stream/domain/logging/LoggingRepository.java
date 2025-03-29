package teamkim.stream.domain.logging;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggingRepository extends JpaRepository<LoggingEntity, Long> {

}