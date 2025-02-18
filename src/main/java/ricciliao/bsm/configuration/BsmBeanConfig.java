package ricciliao.bsm.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.filter.BsmFilter;
import ricciliao.bsm.repository.BsmCodeDetailRepository;
import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.restservice.dto.EmailCacheDto;
import ricciliao.common.component.aspect.DynamicAspectAutoConfiguration;
import ricciliao.common.component.cache.CacheConstants;
import ricciliao.common.component.cache.pojo.ConsumerIdentifierDto;
import ricciliao.common.component.cache.service.CacheRestService;

import java.util.TimeZone;

@Configuration
@ImportAutoConfiguration({
        DynamicAspectAutoConfiguration.class,
})
public class BsmBeanConfig implements WebMvcConfigurer {

    @Bean
    public ObjectMapper objectMapper(@Autowired BsmProps bsmProps) {
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

    @Bean
    public CacheRestService<CaptchaCacheDto> captchaCacheRestService(@Autowired RestTemplate bsmRestTemplate,
                                                                     @Autowired BsmProps bsmProps) {
        return new CacheRestService<>(
                bsmProps.getConsumerCacheProps().getOperation(),
                new ConsumerIdentifierDto(
                        bsmProps.getConsumerCacheProps().getConsumer(),
                        CacheConstants.CACHE_STORE_NAME_FOR_CAPTCHA
                ),
                bsmRestTemplate
        );
    }

    @Bean
    public CacheRestService<EmailCacheDto> emailCacheRestService(@Autowired RestTemplate bsmRestTemplate,
                                                                 @Autowired BsmProps bsmProps) {
        return new CacheRestService<>(
                bsmProps.getConsumerCacheProps().getOperation(),
                new ConsumerIdentifierDto(
                        bsmProps.getConsumerCacheProps().getConsumer(),
                        CacheConstants.CACHE_STORE_NAME_FOR_EMAIL
                ),
                bsmRestTemplate
        );
    }

}
