package teamkim.stream.domain.logging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamkim.stream.domain.logging.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
}
