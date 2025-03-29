package teamkim.stream.domain.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void saveLog(ClassType classType, Direction direction) {
        LogEntity log = new LogEntity(classType, LocalDateTime.now(), direction);
        logRepository.save(log);
    }
}
