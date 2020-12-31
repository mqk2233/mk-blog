package com.mk.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MK
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "blog api文档",
                version = "1.0"
        ),
        externalDocs = @ExternalDocumentation(description = "参考文档",
                url = "https://springdoc.org/#demos"
        )
)
public class MkBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MkBlogApplication.class, args);
    }

}
