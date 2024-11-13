package ricciliao.bsm.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
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

        return
                new OpenAPI()
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
                        .components(
                                new Components()
                                        .addSecuritySchemes(
                                                HttpHeaders.AUTHORIZATION,
                                                new SecurityScheme()
                                                        .name(HttpHeaders.AUTHORIZATION)
                                                        .type(SecurityScheme.Type.HTTP)
                                                        .scheme("bearer")
                                        )
                        );
    }

    @Bean
    public OpenApiCustomizer globalHeaderOpenApiCustomizer() {
        Parameter globalHeader1 =
                new HeaderParameter()
                        .name("Your-Header-Name-1")
                        .description("Description of your header 1")
                        .required(true);
        Parameter globalHeader2 =
                new HeaderParameter()
                        .name("Your-Header-Name-2")
                        .description("Description of your header 2")
                        .required(true);

        return openApi ->
                openApi.getPaths()
                        .values()
                        .forEach(pathItem ->
                                pathItem.readOperations()
                                        .forEach(operation ->
                                                operation
                                                        .addParametersItem(globalHeader1)
                                                        .addParametersItem(globalHeader2)
                                        )
                        );
    }

}
