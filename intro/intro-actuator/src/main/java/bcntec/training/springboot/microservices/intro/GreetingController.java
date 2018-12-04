package bcntec.training.springboot.microservices.intro;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class GreetingController {
    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    TPSHealth health;
    MeterRegistry counterService;
    MeterRegistry gaugeService;

    @Autowired
    GreetingController(TPSHealth health, MeterRegistry counterService, MeterRegistry gaugeService) {
        this.health = health;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @CrossOrigin
    @RequestMapping("/")
    Greet greet() {
        logger.info("Serving Request....!!!");
        health.updateTx();
        this.counterService.counter("greet.txnCount");
        gaugeService.gauge("greet.customgauge", 1.0);
        return new Greet("Hello World!");
    }


}
