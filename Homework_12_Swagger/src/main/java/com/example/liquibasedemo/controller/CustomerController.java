package com.example.liquibasedemo.controller;

import com.example.liquibasedemo.Dto.CustomerDto;
import com.example.liquibasedemo.entity.Customer;
import com.example.liquibasedemo.persistence.CustomerRepository;
import com.example.liquibasedemo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/crud/customer")
@RequiredArgsConstructor
@Api(value = "Customer CRUD operations", description = "Customer CRUD operations")
public class CustomerController {
    private final CustomerService customerService;

    @ApiOperation(value = "Enumerates all Customer entities")
    @GetMapping
    public List<CustomerDto> enumerate() {
        return customerService.findAll().stream()
                .map(CustomerDto::new)
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Store given Customer entity")
    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @ApiOperation(value = "Retrieves Customer entity by it ID")
    @GetMapping("/{id}")
    public CustomerDto get(@PathVariable("id") String id) {
        return new CustomerDto (customerService.getCustomer(id));
    }

    //TODO: добавить и проаннотировать операции удаления сущности Customer, и создания новой пустой сущности Customer

    @ApiOperation(value = "Delete customer entity by it ID")
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") String id) {
        customerService.deleteById(id);
    }

    @ApiOperation(value = "Update given Customer entity with new data")
    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.save(customer);
    }
}
