package dagimon.springframework.sf5_rest_brewery_client.web.client;

import dagimon.springframework.sf5_rest_brewery_client.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerClientTest {

    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerById() {
        CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void addNewCustomer() {
        CustomerDto customerDto = createCustomerSampleDto();
        URI customerURI = customerClient.addNewCustomer(customerDto);
        assertNotNull(customerURI);
    }

    @Test
    void updateCustomer() {
        CustomerDto customerDto = createCustomerSampleDto();
        customerClient.updateCustomer(customerDto.getId(), customerDto);
    }

    @Test
    void deleteCustomer() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }

    private CustomerDto createCustomerSampleDto() {
        return CustomerDto.builder().id(UUID.randomUUID()).name("John Smith").build();

    }
}