package ricciliao.bsm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.filter.BsmFilter;
import ricciliao.bsm.repository.BsmCodeDetailRepository;
import ricciliao.x.log.MdcSupportFilter;

@Configuration
public class BsmBeanConfig implements WebMvcConfigurer {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public FilterRegistrationBean<BsmFilter> httpServletWrapperFilter() {

        FilterRegistrationBean<BsmFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BsmFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        registrationBean.setName("httpServletWrapperFilter");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<MdcSupportFilter> mdcSupportFilter() {
        FilterRegistrationBean<MdcSupportFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MdcSupportFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registrationBean.setName("mdcSupportFilter");

        return registrationBean;
    }

    @Bean
    public BsmCodeListComponent codeListComponent(@Autowired BsmCodeDetailRepository bsmCodeDetailRepository) {

        return new BsmCodeListComponent(bsmCodeDetailRepository);
    }

    @Bean
    public RestTemplate messageRestTemplate() {

        return new RestTemplateBuilder()
                .messageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
                .build();
    }

}
