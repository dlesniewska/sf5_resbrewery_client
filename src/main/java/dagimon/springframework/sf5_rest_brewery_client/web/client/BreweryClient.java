package dagimon.springframework.sf5_rest_brewery_client.web.client;

import dagimon.springframework.sf5_rest_brewery_client.web.model.BeerDto;
import dagimon.springframework.sf5_rest_brewery_client.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sf5.brewery", ignoreUnknownFields = false)
public class BreweryClient {
    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID beerUUID) {
        return restTemplate.getForObject(prepareBeerURI(beerUUID), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID beerUUID, BeerDto beerDto) {
        restTemplate.put(prepareBeerURI(beerUUID), beerDto);
    }

    public void deleteBeer(UUID beerUUID) {
        restTemplate.delete(prepareBeerURI(beerUUID));
    }

    private String prepareBeerURI(UUID beerUUID) {
        return apihost + BEER_PATH_V1 + beerUUID.toString();
    }

    public CustomerDto getCustomerById(UUID customerUUID) {
        return restTemplate.getForObject(prepareCustomerURI(customerUUID), CustomerDto.class);
    }

    public URI addNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID customerUUID, CustomerDto customerDto) {
        restTemplate.put(prepareCustomerURI(customerUUID), customerDto);
    }

    public void deleteCustomer(UUID customerUUID) {
        restTemplate.delete(prepareCustomerURI(customerUUID));
    }

    private String prepareCustomerURI(UUID beerUUID) {
        return apihost + CUSTOMER_PATH_V1 + beerUUID.toString();
    }

}
