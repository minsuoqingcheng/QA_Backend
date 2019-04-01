package cn.nju.edu.se.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MySwaggerConfig {

    @Bean
    public Docket customDocket() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).useDefaultResponseMessages(false).select().apis(input -> {
            Class<?> declaringClass = input.declaringClass();
            return declaringClass.isAnnotationPresent(RestController.class);
        }).build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("", "", "");
        return (new ApiInfoBuilder()).title("后台API接口").description("后台API接口").contact(contact).version("1.1.0").build();
    }
}