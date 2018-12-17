package com.ricciliao.bsm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;


@SpringBootApplication(scanBasePackages = {"com.ricciliao.bsm"})
@MapperScan(
        basePackages = "com.ricciliao.bsm",
        sqlSessionFactoryRef = "sqlSessionFactory",
        sqlSessionTemplateRef = "sqlSessionTemplate",
        annotationClass = Repository.class
)

public class BsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(BsmApplication.class, args);
    }

}
