package bcntec.training.springboot.microservices.scalecloud.checkin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;


interface CheckInSource {
    public static String CHECKINQ = "checkInQ";

    @Output("checkInQ")
    public MessageChannel checkInQ();

}

@EnableBinding(CheckInSource.class)
@Component
public class Sender {

    /**
     * RabbitMessagingTemplate template;
     *
     * @Autowired Sender(RabbitMessagingTemplate template){
     * this.template = template;
     * }
     * @Bean Queue queue() {
     * return new Queue("CheckInQ", false);
     * }
     * <p>
     * public void send(Object message){
     * template.convertAndSend("CheckInQ", message);
     * }
     **/

    @Output(CheckInSource.CHECKINQ)
    @Autowired
    private MessageChannel messageChannel;

    public Sender() {

    }

    public void send(Object message) {
        //template.convertAndSend("InventoryQ", message);
        messageChannel.send(MessageBuilder.withPayload(message).build());
    }
}