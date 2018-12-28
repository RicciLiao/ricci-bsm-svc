package com.ricciliao.bsm;

import com.ricciliao.bsm.interceptor.PageConInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(scanBasePackages = {"com.ricciliao.bsm"})
@MapperScan(
        basePackages = "com.ricciliao.bsm",
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)

public class BsmApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BsmApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(new PageConInterceptor());
        ir.addPathPatterns("/bsm/*");
    }

}
