package demo.project.lambok.five.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;

import demo.project.lambok.five.model.Beer;
import demo.project.lambok.five.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Controller
public class BeerController {
    private final BeerService beerService;

    public Beer getBeerById(UUID id) {
        log.debug("Get Beer by Id  -  in Controller");

        return beerService.getBeerById(id);
    }

}