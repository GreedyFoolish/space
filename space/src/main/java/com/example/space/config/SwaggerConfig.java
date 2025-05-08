package com.example.space.config;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("统一响应 API")
                        .version("1.0")
                        .description("基于 ResponseEntity<T> 的统一响应封装"));
    }

    // 可选：按需分组显示接口
    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("用户管理")
                .pathsToMatch("/api/user/**")
                .build();
    }

    @Bean
    public OpenApiCustomizer openApiGenericResponseCustomizer() {
        return openApi -> openApi.getPaths().forEach((pathKey, pathItem) -> {
            pathItem.readOperations().forEach(operation -> {
                operation.getResponses().forEach((responseCode, apiResponse) -> {
                    if (apiResponse.getContent() != null && !apiResponse.getContent().isEmpty()) {
                        apiResponse.getContent().forEach((mediaType, mediaTypeObject) -> {
                            if (mediaTypeObject.getSchema() != null &&
                                    "#/components/schemas/ResponseEntity".equals(mediaTypeObject.getSchema().get$ref())) {

                                Object dataProperty = mediaTypeObject.getSchema().getProperties().get("data");
                                if (dataProperty instanceof Schema<?>) {
                                    String actualTypeName = ((Schema<?>) dataProperty).get$ref();

                                    if (actualTypeName != null) {
                                        Schema<?> newSchema = new Schema<>().$ref(actualTypeName);

                                        // 创建新的对象结构，包含 code, message, data
                                        Schema<?> wrappedSchema = new Schema<>()
                                                .type("object")
                                                .description("统一响应结构")
                                                .addProperty("code", new Schema<Integer>().type("integer").example(200))
                                                .addProperty("message", new Schema<String>().type("string").example("success"))
                                                .addProperty("data", newSchema);

                                        mediaTypeObject.setSchema(wrappedSchema);
                                    }
                                }
                            }
                        });
                    }
                });
            });
        });
    }

}
