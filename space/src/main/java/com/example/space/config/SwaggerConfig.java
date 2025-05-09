package com.example.space.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("统一响应 API")
//                        .version("1.0")
//                        .description("基于 ResponseEntity<T> 的统一响应封装"));
//    }

//    @Bean
//    public OpenApiCustomizer openApiCustomizer() {
//        return openApi -> openApi.getComponents()
//                .addSchemas("ResponseEntity", new Schema<>()
//                        .type("object")
//                        .description("统一响应结构")
//                        .addProperties("code", new Schema<>().type("integer").example(200))
//                        .addProperties("message", new Schema<>().type("string").example("success"))
//                        .addProperties("data", new Schema<>().type("object")));
//    }

}
