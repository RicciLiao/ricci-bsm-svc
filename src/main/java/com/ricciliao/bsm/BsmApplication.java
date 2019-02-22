package com.ricciliao.bsm;

import com.ricciliao.bsm.interceptor.PageConInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;


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
        InterceptorRegistration pageConIr = registry.addInterceptor(new PageConInterceptor());
        pageConIr.addPathPatterns("/bsm/*");

        registry.addInterceptor(localeChangeInterceptor);

    }

    // i18n
    public LocaleChangeInterceptor localeChangeInterceptor = null;
    @Bean(name = "localeResolver")
    public LocaleResolver initLocaleResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return sessionLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        if(localeChangeInterceptor == null){
            localeChangeInterceptor = new LocaleChangeInterceptor();
            return localeChangeInterceptor;
        }
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

}
