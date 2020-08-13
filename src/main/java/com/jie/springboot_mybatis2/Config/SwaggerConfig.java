package com.jie.springboot_mybatis2.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //配置显示哪些api接口
    @Bean
    public Docket docketGet(Environment environment){

        //获取项目的环境，设置显示的swagger环境仅为dev时
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .groupName("GET方法")
                //配置api接口读取的范围
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jie.springboot_mybatis2.Controller"))//这个包下的api
                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))//仅get方法
                .build();
    }
    @Bean
    public Docket docketPost(Environment environment){

        //获取项目的环境，设置显示的swagger环境仅为dev时
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .groupName("POST方法")
                //配置api接口读取的范围
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jie.springboot_mybatis2.Controller"))
                .apis(RequestHandlerSelectors.withMethodAnnotation(PostMapping.class))
                .build();
    }
    @Bean
    public Docket docket(Environment environment){

        //获取项目的环境，设置显示的swagger环境仅为dev时
        boolean flag = environment.acceptsProfiles(Profiles.of("dev"));
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(flag)
                .groupName("默认");

    }
}
