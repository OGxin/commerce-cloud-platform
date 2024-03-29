package com.ozx.ozxshopserviceapimember.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: 以yml文件配置方式配置swagger，类配置swagger属性已弃用
 * @Author ou.zhenxing
 * @Date 2020/1/9 16:32
 * @Version： 1.0
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
            return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//                    .apis(RequestHandlerSelectors.basePackage("com.ozx.ozxshopserviceapimember.service"))
//                    .apis(RequestHandlerSelectors.withClassAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger API")
                    .description("接口文档")
                        .termsOfServiceUrl("https://github.com/OGxin")
                            .contact(new Contact("Gxin","https://github.com/OGxin","779855839@qq.com"))
                                .version("2.0")
                                    .build();
    }
}



