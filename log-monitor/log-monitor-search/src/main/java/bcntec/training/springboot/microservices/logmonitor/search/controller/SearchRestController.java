package bcntec.training.springboot.microservices.logmonitor.search.controller;

import bcntec.training.springboot.microservices.logmonitor.search.component.SearchComponent;
import bcntec.training.springboot.microservices.logmonitor.search.entity.Flight;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@RefreshScope
@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {
    TPMCounter tpm = new TPMCounter();
    MeterRegistry gaugeService;
    private SearchComponent searchComponent;
    @Value("${orginairports.shutdown}")
    private String originAirportShutdownList;


    @Autowired
    public SearchRestController(SearchComponent searchComponent, MeterRegistry gaugeService) {
        this.gaugeService = gaugeService;
        this.searchComponent = searchComponent;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    List<Flight> search(@RequestBody SearchQuery query) {
        log.info("Input : " + query);
        if (Arrays.asList(originAirportShutdownList.split(",")).contains(query.getOrigin())) {
            log.info("The origin airport is in shutdown state");
            return new ArrayList<Flight>();
        }
        tpm.increment();
        gaugeService.gauge("tpm", tpm.count.intValue());

        return searchComponent.search(query);
    }

    @RequestMapping("/hub")
    String getHub() {
        log.info("Searching for Hub, received from search-apigateway ");
        return "SFO";
    }


}


class TPMCounter {
    LongAdder count;
    Calendar expiry = null;

    TPMCounter() {
        reset();
    }

    void reset() {
        count = new LongAdder();
        expiry = Calendar.getInstance();
        expiry.add(Calendar.MINUTE, 1);
    }

    boolean isExpired() {
        return Calendar.getInstance().after(expiry);
    }


    void increment() {
        if (isExpired()) {
            reset();
        }
        count.increment();
    }

}