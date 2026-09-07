package example.guesthousecustomerservice.services.impl;





import example.guesthousecustomerservice.dtos.CustomerDTO;
import example.guesthousecustomerservice.models.Customer;
import example.guesthousecustomerservice.repositories.CustomerRepository;
import example.guesthousecustomerservice.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service


public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;


    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(c -> new CustomerDTO(c.getName(), c.getId()))
                .toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow();
        return new CustomerDTO(customer.getName(), customer.getId());
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO.getName());
        customer.setId(customerDTO.getId());
        customerRepository.save(customer);

    }
//    @Override
//    public void delete(Long id) {
//        if (bookingRepository.existsByCustomerId(id)) {
//            throw new RuntimeException("Can't remove customers with active bookings!");
//        }
//        customerRepository.deleteById(id);
//    }


}

