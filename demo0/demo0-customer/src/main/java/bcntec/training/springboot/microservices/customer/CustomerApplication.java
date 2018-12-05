package bcntec.training.springboot.microservices.customer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }


    @Bean
    CommandLineRunner init(CustomerRespository customerRepository) {
        return (evt) -> {
            customerRepository.save(new Customer("Adam", "adam@boot.com"));
            customerRepository.save(new Customer("John", "john@boot.com"));
            customerRepository.save(new Customer("Smith", "smith@boot.com"));
            customerRepository.save(new Customer("Edgar", "edgar@boot.com"));
            customerRepository.save(new Customer("Martin", "martin@boot.com"));
            customerRepository.save(new Customer("Tom", "tom@boot.com"));
            customerRepository.save(new Customer("Sean", "sean@boot.com"));
        };
    }


}


