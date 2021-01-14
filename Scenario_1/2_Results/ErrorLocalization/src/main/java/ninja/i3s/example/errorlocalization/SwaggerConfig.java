package ninja.i3s.example.errorlocalization;

//imports
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @return
     */
    @Bean
    public Docket taskApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("validation-api")
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo())
            .select()
                .paths(regex("/api/validate.*"))
            .build();
    }

    /**
     *
     * @return
     */
    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Error localization service")
                .description("API for validating data - i3s services")
                .version("1.0")
                .build();
    }
}