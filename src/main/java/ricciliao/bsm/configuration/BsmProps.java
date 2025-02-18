package ricciliao.bsm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ricciliao.common.component.cache.consumer.ConsumerCacheProperties;
import ricciliao.common.component.props.DefaultProperties;

@EnableConfigurationProperties({
        ConsumerCacheProperties.class,
})
@Component("bsmProps")
public class BsmProps extends DefaultProperties {

    public BsmProps(@Autowired ConsumerCacheProperties consumerCacheProperties) {
        super();
        this.consumerCacheProps = consumerCacheProperties;
    }

    private final ConsumerCacheProperties consumerCacheProps;

    public ConsumerCacheProperties getConsumerCacheProps() {
        return consumerCacheProps;
    }
}
