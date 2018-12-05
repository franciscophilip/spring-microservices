package bcntec.training.springboot.demo1.search.controller;

import bcntec.training.springboot.demo1.search.component.SearchComponent;
import bcntec.training.springboot.demo1.search.entity.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/search")
class SearchRestController {

    private SearchComponent searchComponent;

    @Autowired
    public SearchRestController(SearchComponent searchComponent) {
        this.searchComponent = searchComponent;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    List<Flight> search(@RequestBody SearchQuery query) {
        System.out.println("Input : " + query);
        return searchComponent.search(query);
    }

}
