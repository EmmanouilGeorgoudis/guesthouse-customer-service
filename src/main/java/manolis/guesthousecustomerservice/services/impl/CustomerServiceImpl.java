package com.example.guesthousebookingsystem.services.impl;


import com.example.guesthousebookingsystem.dtos.CustomerDTO;
import com.example.guesthousebookingsystem.models.Customer;
import com.example.guesthousebookingsystem.repositories.BookingRepository;
import com.example.guesthousebookingsystem.repositories.CustomerRepository;
import com.example.guesthousebookingsystem.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service


public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final BookingRepository bookingRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.bookingRepository = bookingRepository;
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
    @Override
    public void delete(Long id) {
        if (bookingRepository.existsByCustomerId(id)) {
            throw new RuntimeException("Can't remove customers with active bookings!");
        }
        customerRepository.deleteById(id);
    }


}

