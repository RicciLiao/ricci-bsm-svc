package ricciliao.bsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.filter.BsmFilter;
import ricciliao.bsm.repository.BsmCodeDetailRepository;

@Configuration
public class BsmBeanConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<BsmFilter> httpServletWrapperFilter() {

        FilterRegistrationBean<BsmFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BsmFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("httpServletWrapperFilter");

        return registrationBean;
    }

    @Bean
    public BsmCodeListComponent codeListComponent(@Autowired BsmCodeDetailRepository bsmCodeDetailRepository) {

        return new BsmCodeListComponent(bsmCodeDetailRepository);
    }

    @Bean
    public RestTemplate bsmRestTemplate() {

        return new RestTemplate();
    }

}
