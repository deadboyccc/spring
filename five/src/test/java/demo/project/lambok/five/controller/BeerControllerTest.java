package demo.project.lambok.five.controller;

import java.util.UUID;

import org.apache.tomcat.util.http.parser.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import demo.project.lambok.five.service.BeerService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    // @Autowired
    // BeerController beerController;
    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BeerService beerService;

    @Test
    void testGetBeerById() throws Exception {
        mockMvc
                .perform(
                        get("/api/v1/beer/" + UUID.randomUUID())
                                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // System.out.println(beerController.getBeerById(UUID.randomUUID()));

    }
}
