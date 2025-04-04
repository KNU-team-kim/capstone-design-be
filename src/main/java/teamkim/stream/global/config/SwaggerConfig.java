package teamkim.stream.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Logging API", version = "v1", description = "Logging Service API Documentation"))
public class SwaggerConfig {
}
