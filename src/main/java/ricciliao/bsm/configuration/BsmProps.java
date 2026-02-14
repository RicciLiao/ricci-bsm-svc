package ricciliao.bsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.starter.XProperties;

@Configuration("bsmProps")
public class BsmProps implements ApplicationProperties {

    private final XProperties xProperties;

    public BsmProps(@Autowired XProperties xProperties) {
        this.xProperties = xProperties;
    }

    public XProperties getxProperties() {
        return xProperties;
    }
}
