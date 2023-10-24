package com.example.springmvc_thuc_tap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")  // Đặt đường dẫn truy cập tài nguyên tĩnh
                .addResourceLocations("classpath:/static/")  // Đặt thư mục lưu trữ tài nguyên tĩnh
                .setCachePeriod(600);  // Đặt thời gian cache (600 giây)
    }
}

