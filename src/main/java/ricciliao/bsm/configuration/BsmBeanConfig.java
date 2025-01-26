package ricciliao.bsm.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import ricciliao.bsm.aspect.ControllerAspect;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.filter.HttpServletWrapperFilter;
import ricciliao.bsm.repository.BsmCodeDetailRepository;
import ricciliao.dynamic.aop.DynamicPointcutAdvisor;

import java.util.TimeZone;

@Configuration
public class BsmBeanConfig {

    private BsmProps bsmProps;

    @Autowired
    public void setBsmProps(BsmProps bsmProps) {
        this.bsmProps = bsmProps;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setTimeZone(TimeZone.getTimeZone(bsmProps.getTimeZone()));
        // objectMapper java.time.LocalDate/LocalDateTime
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Bean
    public DynamicPointcutAdvisor controllerAspect() {

        return new DynamicPointcutAdvisor(
                bsmProps.getDynamicAopPointCutController(),
                new ControllerAspect()
        );
    }

    @Bean
    public FilterRegistrationBean<HttpServletWrapperFilter> wrapperFilter() {

        FilterRegistrationBean<HttpServletWrapperFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new HttpServletWrapperFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("httpServletWrapperFilter");

        return registrationBean;
    }

    @Bean
    public BsmCodeListComponent codeListComponent(@Autowired BsmCodeDetailRepository bsmCodeDetailRepository) {

        return new BsmCodeListComponent(bsmCodeDetailRepository);
    }

}
