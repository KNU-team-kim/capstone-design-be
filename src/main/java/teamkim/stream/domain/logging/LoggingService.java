package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoggingService {

    @Autowired
    private LoggingRepository loggingRepository;

    public void saveLog(ClassType classType, Direction direction) {
        LoggingEntity log = new LoggingEntity(classType, LocalDateTime.now(), direction);
        loggingRepository.save(log);
    }
}
