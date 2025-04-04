package teamkim.stream.domain.logging.dto;

import lombok.Getter;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;

@Getter
public class LogRequestDto {
    private ClassType classType;
    private float confidence;
    private String fileName;
    private String imageUrl;
    private DirectionType directionType;
}
