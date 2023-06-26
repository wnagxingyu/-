package com.example.wxy.config;


import com.example.wxy.wxy4.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor());//拦截全部路径
        //registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/user/**");//拦截全部路径
    }
}
