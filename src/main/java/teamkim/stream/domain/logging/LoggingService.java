package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoggingService {

    private final LoggingRepository loggingRepository;

    @Autowired
    public LoggingService(LoggingRepository loggingRepository) {
        this.loggingRepository = loggingRepository;
    }

    public void saveLog(ClassType classType, Direction direction) {
        LoggingEntity log = new LoggingEntity(classType, LocalDateTime.now(), direction);
        loggingRepository.save(log);
    }

    public List<LoggingEntity> getAllLogs() {
        return loggingRepository.findAll();
    }

    public List<LoggingEntity> getLogsByClassType(ClassType classType) {
        return loggingRepository.findByClassType(classType);
    }

    public List<LoggingEntity> getLogsByDirection(Direction direction) {
        return loggingRepository.findByDirection(direction);
    }
}
