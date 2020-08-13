package com.jie.springboot_mybatis2.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
        interceptor.addPathPatterns("/**").
        excludePathPatterns("/login","/logined","/sessionout","/","/signedup","/signup","/css/*","/img/*","/js/*","/error/*","/base");
    }

    //动态加载静态资源，上传头像能及时刷新
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/profilepic/**")
        .addResourceLocations("file:/Users/jie/IdeaProjects/springboot_mybatis2/src/main/resources/static/profilepic/");
//        String path = System.getProperty("user.dir")+"/src/main/resources/static/profilepic/";
//        System.out.println(path);
//        registry.addResourceHandler("/profilepic/**").addResourceLocations("file:"+path);
    }
}
