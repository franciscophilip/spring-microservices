package bcntec.training.springboot.microservices.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Lazy
class CustomerRegistrar {

    CustomerRespository customerRespository;
    Sender sender;

    @Autowired
    CustomerRegistrar(CustomerRespository customerRespository, Sender sender) {
        this.customerRespository = customerRespository;
        this.sender = sender;
    }


    public Mono<Customer> registerMono(Mono<Customer> monoCustomer) {
        monoCustomer.doOnNext(customer -> {
            if (customerRespository.findByName(customer.getName()).isPresent())
                System.out.println("Duplicate Customer");
            else {
                customerRespository.save(customer);
                sender.send(customer.getEmail());
            }
        }).subscribe();
        return monoCustomer;
    }

    // ideally repository will return a Mono object
    public Mono<Customer> register(Customer customer) {
        if (customerRespository.findByName(customer.getName()).isPresent())
            System.out.println("Duplicate Customer. No Action required");
        else {
            customerRespository.save(customer);
            sender.send(customer.getEmail());
        }
        return Mono.just(customer);
    }
}
