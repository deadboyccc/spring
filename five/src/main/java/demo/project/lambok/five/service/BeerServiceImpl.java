package demo.project.lambok.five.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import ch.qos.logback.core.util.StringUtil;
import demo.project.lambok.five.model.Beer;
import demo.project.lambok.five.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID, Beer> beerMap;

    // bootstrapping data
    public BeerServiceImpl() {
        beerMap = new HashMap<>();
        Beer beer1 = Beer.builder()

                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())

                .build();

        Beer beer2 = Beer.builder()

                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())

                .build();

        Beer beer3 = Beer.builder()

                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())

                .build();

        beerMap.put(beer1.getId(), beer1);
        beerMap.put(beer2.getId(), beer2);
        beerMap.put(beer3.getId(), beer3);

    }

    @Override
    // list beers
    public List<Beer> listBeers() {

        return new ArrayList<>(beerMap.values());
    }

    // get by id
    @Override
    public Beer getBeerById(UUID id) {

        log.debug("Get Beer By Id Service was called");
        return beerMap.get(id);

    }

    @Override

    public Beer saveNewBeer(Beer beer) {

        // mimicking persistance layer
        Beer savedBeer = Beer.builder()

                .id(UUID.randomUUID())

                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .version(beer.getVersion())

                .build();

        beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'saveNewBeer'");
    }

    @Override
    public void updateBeerById(UUID beerId, Beer beer) {
        Beer oldBeer = beerMap.get(beerId);

        if (oldBeer != null) {

            oldBeer.setBeerName(beer.getBeerName());
            oldBeer.setBeerStyle(beer.getBeerStyle());
            oldBeer.setQuantityOnHand(beer.getQuantityOnHand());
            oldBeer.setUpc(beer.getUpc());
            oldBeer.setPrice(beer.getPrice());
            oldBeer.setVersion(beer.getVersion());
            oldBeer.setUpdateDate(LocalDateTime.now());

        }

        beerMap.put(beerId, oldBeer);

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'updateBeerById'");
    }

    @Override
    public void deleteById(UUID beerId) {

        beerMap.remove(beerId);
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void patchById(UUID beerId, Beer beer) {

        Beer existingBeer = beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }

        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }

        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }

        if (StringUtils.hasText(existingBeer.getUpc())) {
            existingBeer.setUpdateDate(LocalDateTime.now());
        }

        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'patchById'");
    }

}
