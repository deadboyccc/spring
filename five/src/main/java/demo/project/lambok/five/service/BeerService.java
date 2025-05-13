package demo.project.lambok.five.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import demo.project.lambok.five.model.Beer;

public interface BeerService {
    Beer getBeerById(UUID id);

    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    void updateBeerById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    void patchById(UUID beerId, Beer beer);

}
