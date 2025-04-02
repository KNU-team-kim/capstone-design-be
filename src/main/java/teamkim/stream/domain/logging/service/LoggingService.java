package teamkim.stream.domain.logging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;
import teamkim.stream.domain.logging.entity.LoggingEntity;
import teamkim.stream.domain.logging.repository.LoggingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoggingService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public LoggingService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public void saveLog(ClassType classType, float confidence, String imageUrl, DirectionType directionType) {
        LoggingEntity log = new LoggingEntity(classType, confidence, imageUrl, LocalDateTime.now(), directionType);
        loggingRepository.save(log);
    }

    public List<LoggingEntity> getAllLogs() {
        return loggingRepository.findAll();
    }

    public List<LoggingEntity> getLogsByClassType(ClassType classType) {
        return loggingRepository.findByClassType(classType);
    }

    public List<LoggingEntity> getLogsByDirectionType(DirectionType directionType) {
        return loggingRepository.findByDirectionType(directionType);
    }
}