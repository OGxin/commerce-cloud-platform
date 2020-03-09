package com.ozx.ozxshopserviceweixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
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
                    .apis(RequestHandlerSelectors.basePackage("com.ozx.ozxshopserviceweixin.controller"))
                    .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger API")
                    .description("接口文档")
                        .termsOfServiceUrl("")
                            .contact(new Contact("ozx","","779855839@qq.com"))
                                .version("1.0")
                                    .build();
    }
}



