package bcntec.training.springboot.microservices.intro;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    
    @Bean
	CommandLineRunner init(CustomerRespository customerRepository) {
		return (evt) ->  {
							customerRepository.save(new Customer("Adam","adam@boot.com"));
							customerRepository.save(new Customer("John","john@boot.com"));
							customerRepository.save(new Customer("Smith","smith@boot.com"));
							customerRepository.save(new Customer("Edgar","edgar@boot.com"));
							customerRepository.save(new Customer("Martin","martin@boot.com"));
							customerRepository.save(new Customer("Tom","tom@boot.com"));
							customerRepository.save(new Customer("Sean","sean@boot.com"));
						};
	}
    
 
}


