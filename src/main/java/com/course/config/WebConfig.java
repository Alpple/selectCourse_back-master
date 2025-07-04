package com.course.config;

import com.course.config.themis.ThemisInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 自定义拦截器，解决跨域问题
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ThemisInterceptor themisInterceptor;

    public WebConfig(ThemisInterceptor themisInterceptor) {
        this.themisInterceptor = themisInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(themisInterceptor)
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .allowedOrigins("*");
    }
}
