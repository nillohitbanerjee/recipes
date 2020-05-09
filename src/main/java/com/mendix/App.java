package com.mendix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!O
 *
 */
@ComponentScan(basePackages = { "com.mendix"} )
@SpringBootApplication
@EnableJpaRepositories("com.mendix.dao")
@EntityScan("com.mendix.dbModel")
@EnableSwagger2
public class App
{
    public static void main( String[] args )
    {

        SpringApplication.run(App.class, args);
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.mendix")).build();
    }
}
