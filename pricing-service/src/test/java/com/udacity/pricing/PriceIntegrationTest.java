package com.udacity.pricing;


import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PriceIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    public void getPrice() throws Exception {
        String url = "http://localhost:" + port + "/services/price";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("vehicleId", 1L);
        Price response = this.testRestTemplate.getForObject(builder.toUriString(),
                Price.class);
        assertThat(response.getPrice(), notNullValue());
        assertThat(response.getVehicleId(), notNullValue());
    }
}
