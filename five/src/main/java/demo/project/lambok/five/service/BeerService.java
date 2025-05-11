package demo.project.lambok.five.service;

import java.util.UUID;

import demo.project.lambok.five.model.Beer;

public interface BeerService {
    Beer getBeerById(UUID id);

}
