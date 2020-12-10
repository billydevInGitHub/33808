package billydev.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    private String applicationName="33808exp202";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes(BASIC_AUTH_SECURITY_SCHEME,
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
                .info(new Info().title(applicationName));
    }

    @Bean
    public GroupedOpenApi classApi() {
        return GroupedOpenApi.builder().group("class").pathsToMatch("/class/**").build();
    }

    @Bean
    public GroupedOpenApi studentApi() {
        return GroupedOpenApi.builder().group("student").pathsToMatch("/student/**").build();
    }

    public static final String BASIC_AUTH_SECURITY_SCHEME = "basicAuth";

}
