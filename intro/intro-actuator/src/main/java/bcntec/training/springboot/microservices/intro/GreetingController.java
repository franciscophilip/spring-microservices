package bcntec.training.springboot.microservices.intro;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class GreetingController {

    final GreetingRepository repository;
    final ControllerHealth health;
    final MeterRegistry counterService;
    final MeterRegistry gaugeService;

    @Autowired
    GreetingController(GreetingRepository repository, ControllerHealth health, MeterRegistry counterService, MeterRegistry gaugeService) {
        this.repository = repository;
        this.health = health;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @CrossOrigin
    @RequestMapping("/")
    Greet greet() {
        log.info("Serving Request....!!!");
        health.updateTx();
        Counter t = this.counterService.counter("greet.txnCount");
        Double d = gaugeService.gauge("greet.customgauge", 1.0);
        repository.save(new Greeting(health.counter.count.doubleValue()));
        System.out.println(health.counter.count.doubleValue());
        return new Greet("Hello World! " + health.counter.count.doubleValue());
    }


}
