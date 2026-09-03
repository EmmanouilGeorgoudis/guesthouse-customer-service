package com.example.guesthousebookingsystem.services;

import com.example.guesthousebookingsystem.dtos.CustomerDTO;
import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getById(Long id);
    void save(CustomerDTO customerDTO);
    void delete(Long id);
}
