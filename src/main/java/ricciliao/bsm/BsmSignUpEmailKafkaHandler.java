package ricciliao.bsm;

import ricciliao.x.component.kafka.KafkaConsumerHandler;

public class BsmSignUpEmailKafkaHandler implements KafkaConsumerHandler<SendPostKafkaDto> {

    @Override
    public void handle(SendPostKafkaDto message) {
        System.out.println(message);
    }

}
