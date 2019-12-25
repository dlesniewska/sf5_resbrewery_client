package dagimon.springframework.sf5_rest_brewery_client.web.client;

import dagimon.springframework.sf5_rest_brewery_client.web.model.BeerDto;
import dagimon.springframework.sf5_rest_brewery_client.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerById() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        BeerDto beerDto = createBeerSampleDto();
        URI newBeerURI = breweryClient.saveNewBeer(beerDto);
        assertNotNull(newBeerURI);
        System.out.println(newBeerURI.toString());
    }

    @Test
    void updateBeer() {
        BeerDto beerDto = createBeerSampleDto();
        breweryClient.updateBeer(beerDto.getId(), beerDto);
    }

    @Test
    void deleteBeer() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }

    private BeerDto createBeerSampleDto() {
        return BeerDto.builder().id(UUID.randomUUID()).beerName("SantaCroce").build();
    }

    @Test
    void getCustomerById() {
        CustomerDto customerDto = breweryClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void addNewCustomer() {
        CustomerDto customerDto = createCustomerSampleDto();
        URI customerURI = breweryClient.addNewCustomer(customerDto);
        assertNotNull(customerURI);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = createCustomerSampleDto();
        breweryClient.updateCustomer(customerDto.getId(), customerDto);
    }

    @Test
    void deleteCustomer() {
        breweryClient.deleteCustomer(UUID.randomUUID());
    }

    private CustomerDto createCustomerSampleDto() {
        return CustomerDto.builder().id(UUID.randomUUID()).name("John Smith").build();

    }
}