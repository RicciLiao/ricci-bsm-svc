package ricciliao.bsm.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SpringdocConfig {

    private String projectVersion;

    @Value("${spring.application.version}")
    public void setProjectVersion(String projectVersion) {
        this.projectVersion = projectVersion;
    }

    @Bean
    public OpenAPI api() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("A title")
                                .description("A description")
                                .contact(new Contact().name("A contact"))
                                .license(new License().name("A License"))
                                .summary("A summary")
                                .version(projectVersion)
                )
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme().name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")))
                ;
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {

        return GroupedOpenApi.builder()
                .group("bsm")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(openApiCustomizer())
                .build();
    }

    private OpenApiCustomizer openApiCustomizer() {

        return openApi -> {
            for (PathItem pathItem : openApi.getPaths().values()) {
                for (Operation readOperation : pathItem.readOperations()) {
                    readOperation.addParametersItem(new HeaderParameter().name("myHeader4").schema(new StringSchema()));
                    readOperation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
                }
            }

        };

    }
}
