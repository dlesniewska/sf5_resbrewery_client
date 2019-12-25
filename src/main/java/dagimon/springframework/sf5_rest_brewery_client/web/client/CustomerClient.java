package dagimon.springframework.sf5_rest_brewery_client.web.client;

import dagimon.springframework.sf5_rest_brewery_client.web.model.CustomerDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sf5.brewery", ignoreUnknownFields = false)
public class CustomerClient {

    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private final RestTemplate restTemplate;


    public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        ;
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public CustomerDto getCustomerById(UUID customerUUID) {
        return restTemplate.getForObject(prepareURI(customerUUID), CustomerDto.class);
    }

    public URI addNewCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto);
    }

    public void updateCustomer(UUID customerUUID, CustomerDto customerDto) {
        restTemplate.put(prepareURI(customerUUID), customerDto);
    }

    public void deleteCustomer(UUID customerUUID) {
        restTemplate.delete(prepareURI(customerUUID));
    }

    private String prepareURI(UUID beerUUID) {
        return apihost + CUSTOMER_PATH_V1 + beerUUID.toString();
    }
}
