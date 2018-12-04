package bcntec.training.springboot.microservices.intro.hateoas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
class GreetingController {
    @GetMapping("/")
    Greet greet() {
        return new Greet("Hello World!");
    }
}
