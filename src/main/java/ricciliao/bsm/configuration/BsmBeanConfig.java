package ricciliao.bsm.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ricciliao.bsm.filter.BsmFilter;
import ricciliao.x.log.MdcSupportFilter;

@Configuration
@ComponentScan(basePackages = "ricciliao.x.starter.common")
public class BsmBeanConfig implements WebMvcConfigurer {

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

}
