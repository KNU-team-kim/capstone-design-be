package teamkim.stream.domain.logging.dto;

import lombok.Getter;
import teamkim.stream.domain.logging.enums.ClassType;
import teamkim.stream.domain.logging.enums.DirectionType;

import java.util.List;

@Getter
public class LogRequestDto {
    private List<ClassType> classTypes;
    private float confidence;
    private String fileName;
    private DirectionType directionType;
}
