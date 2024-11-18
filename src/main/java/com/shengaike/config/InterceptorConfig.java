package com.shengaike.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        //其中/images是访问图片资源的前缀，比如要访问test1.png。则全路径为：http://localhost:端口号/upload/test1.png
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/Users/hardy/java/Upload/Server_SwiftPro/");//此处为设置服务端存储图片的路径（前段上传到后台的图片保存位置）
    }
}