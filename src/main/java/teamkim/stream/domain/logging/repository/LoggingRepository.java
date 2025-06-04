package teamkim.stream.domain.logging.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;

import java.util.List;

public interface LoggingRepository extends JpaRepository<LoggingEntity, Long> {

    /*
    List<LoggingEntity> findByClassType(ClassType classType);

    List<LoggingEntity> findByDirectionType(DirectionType directionType);
    */

    @Query("select distinct l.id from logging_entity l left join l.classEntityList lc " +
            "where (:classType is null or lc.classType = :classType) " +
            "and (:directionType is null or l.directionType = :directionType)" +
            "order by l.id desc")
    Page<Long> findIdsByOptionsWithPaging(Pageable pageable, ClassType classType, DirectionType directionType);

    @Query("select l from logging_entity l left join fetch l.classEntityList where l.id in :ids order by l.id desc")
    List<LoggingEntity> findLogDetailByIdsFetch(List<Long> ids);
}