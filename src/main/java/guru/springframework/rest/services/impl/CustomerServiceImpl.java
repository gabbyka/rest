package guru.springframework.rest.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import guru.springframework.rest.api.v1.mapper.CustomerMapper;
import guru.springframework.rest.api.v1.model.CustomerDTO;
import guru.springframework.rest.domain.Customer;
import guru.springframework.rest.repositories.CustomerRepository;
import guru.springframework.rest.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        return customerMapper.customerToCustomerDto(customerRepository.findById(id).get());
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        return saveAndReturnDTO(customerMapper.customerDtoToCustomer(customerDTO));
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerDto(savedCustomer);
    }

    @Override
    public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
        Customer customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveAndReturnDTO(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {

            if (customerDTO.getFirstname() != null) {
                customer.setFirstName(customerDTO.getFirstname());
            }

            if (customerDTO.getLastname() != null) {
                customer.setLastName(customerDTO.getLastname());
            }

            CustomerDTO returnDto = customerMapper.customerToCustomerDto(customerRepository.save(customer));

            returnDto.setCustomerUrl("/api/v1/customer/" + id);

            return returnDto;

        }).orElseThrow(RuntimeException::new); // todo implement better exception handling;
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

}
