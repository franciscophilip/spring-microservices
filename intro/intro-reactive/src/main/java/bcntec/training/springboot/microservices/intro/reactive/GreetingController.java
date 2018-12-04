package bcntec.training.springboot.microservices.intro.reactive;

import reactor.core.publisher.Mono;

@RestController
class GreetingController{
	@RequestMapping("/")
    Mono<Greet> greet(){
		return Mono.just(new Greet("Hello World!"));
	}
}
