package guru.springframework.rest.api.v1.mapper.impl;

import org.springframework.stereotype.Component;
import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDTO;
import guru.springframework.rest.domain.Customer;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDto(Customer customer) {
        
        if (customer == null) {
            return null;
        }
        
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstname(customer.getFirstName());
        customerDTO.setLastname(customer.getLastName());

        return customerDTO;
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDTO customerDto) {

        if(customerDto == null) {
            return null;
        }
        
        Customer customer = new Customer();
        
        customer.setFirstName(customerDto.getFirstname());
        customer.setLastName(customerDto.getLastname());
        
        return customer;
    }

}
