package ricciliao.bsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ricciliao.x.component.props.ApplicationProperties;
import ricciliao.x.starter.XProperties;

@Configuration("bsmProps")
public class BsmProps extends ApplicationProperties {

    public BsmProps(@Autowired XProperties xProperties) {
        super();
        this.xProperties = xProperties;
    }

    private final XProperties xProperties;

    public XProperties getxProperties() {
        return xProperties;
    }
}
