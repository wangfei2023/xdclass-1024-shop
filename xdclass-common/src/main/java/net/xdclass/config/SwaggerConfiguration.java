package net.xdclass.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/
@Component
@Data
@EnableOpenApi
public class SwaggerConfiguration {

    @Bean
    public Docket webApiDoc(){
        return new
                Docket(DocumentationType.OAS_30)
                .groupName("用户端接口文档") .pathMapping("/")
// 定义是否开启swagger，false为关
                .enable(true)
//配置api文档元信息
                .apiInfo(apiInfo())
// 选择哪些接口作为swagger的doc发布
                .select()

                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
//正则匹配请求路径，并分配至当前分组 .paths(PathSelectors.ant("/api/**"))
                                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("1024电商平台") .description("微服务接口文档")


                .contact(new Contact("小滴课堂-二 当家小D", "https://xdclass.net",
                        "794666918@qq.com"))
                .version("12").build();
    }
}