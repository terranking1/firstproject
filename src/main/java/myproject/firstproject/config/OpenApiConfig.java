package myproject.firstproject.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("게시판 API")
                .version("v1")
                .description("간단한 게시판 API입니다.");
        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
