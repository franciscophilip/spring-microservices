package bcntec.training.springboot.microservices.intro.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    @GetMapping("/")
    Greet greet() {
        return new Greet("Hello World!");
    }
}
