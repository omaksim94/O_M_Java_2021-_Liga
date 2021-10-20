package com.example.liquibasedemo.service;

import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteById(String id) {
        if (customerRepository.findById(
                UUID.fromString(id)
        ).isPresent()) {
            customerRepository.deleteById(UUID.fromString(id));
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Customer getCustomer(String id) {
        return customerRepository.findById(
                        UUID.fromString(id)
                )
                .get();
    }
}
