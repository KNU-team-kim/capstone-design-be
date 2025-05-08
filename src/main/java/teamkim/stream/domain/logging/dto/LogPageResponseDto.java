package teamkim.stream.domain.logging.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LogPageResponseDto {
    private Integer totalPage;
    private List<LogResponseDto> logs;
}
