package bcntec.training.springboot.microservices.intro.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
class Sender {

    private final RabbitMessagingTemplate template;

    @Autowired
    public Sender(RabbitMessagingTemplate template) {
        this.template = template;
    }

    @Bean
    Queue queue() {
        return new Queue("TestQ", false);
    }

    public void send(String message) {
        template.convertAndSend("TestQ", message);
    }
}
