package bcntec.training.springboot.microservices.intro.messaging;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MessagingApplication implements CommandLineRunner {
    @Autowired
    Sender sender;


    public static void main(String[] args) {
        SpringApplication springApplication =
                new SpringApplicationBuilder()
                        .web(WebApplicationType.NONE)
                        .sources(MessagingApplication.class)
                        .build();

        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        sender.send("Hello Messaging..!!!");
    }

}


