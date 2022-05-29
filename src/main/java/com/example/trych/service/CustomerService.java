package com.example.trych.service;

import com.example.trych.dto.CustomerDto;
import com.example.trych.model.Customer;

import java.util.List;

public interface CustomerService {

    public List<CustomerDto> getAll();
    public CustomerDto getOne(Long id);
    public CustomerDto save(Customer customer);
    public CustomerDto update(Customer customer);
    public void delete(Long id);


}
