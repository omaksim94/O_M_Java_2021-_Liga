package com.example.liquibasedemo.Dto;


import com.example.liquibasedemo.entity.Customer;
import lombok.Data;

@Data
public class CustomerDto {
    private String name;
    private Long rating;

    public CustomerDto(Customer customer){
        this.name = customer.getName();
        this.rating = customer.getRating();
    }
}