package com.example.trych.service;

import com.example.trych.dao.CustomerDao;
import com.example.trych.dto.CustomerDto;
import com.example.trych.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDao dao;

    @Override
    public List<CustomerDto> getAll() {
        List<Customer> customers = dao.findAll();
        List<CustomerDto> customersDto = CustomerDto.converter(customers);
        return customersDto;
    }

    @Override
    public CustomerDto getOne(Long id) {
        Optional<Customer> customer = dao.findById(id);
        if (customer.isEmpty()){
            return null;
        }
        CustomerDto customerDto = new CustomerDto(customer.get());
        return customerDto;
    }

    @Override
    public CustomerDto save(Customer customer) {
        Customer savedCustomer = dao.save(customer);
        CustomerDto savedCustomerDto = new CustomerDto(savedCustomer);
        return savedCustomerDto;
    }

    @Override
    public CustomerDto update(Customer customerForm) {

        Optional<Customer> customerOptional = dao.findById(customerForm.getId());

        Customer customer = customerOptional.get();

        if(customerForm.getName() == null && customerForm.getName() == ""){
            return null;
        }

        if(customerForm.getEmail() == null && customerForm.getEmail() == ""){
            return null;
        }

        if(customerForm.getCpf() == null && customerForm.getCpf() == ""){
            return null;
        }

        if(customerForm.getCep() == null && customerForm.getCep() == ""){
            return null;
        }

        customer.setName(customerForm.getName());
        customer.setEmail(customerForm.getEmail());
        customer.setCpf(customerForm.getCpf());
        customer.setCep(customerForm.getCep());

        dao.save(customer);

        return new CustomerDto(customer);

    }

    @Override
    public void delete(Long id) {
//        Optional<Customer> customerOptional = dao.findById(id);
//
//        dao.delete(customerOptional.get());
        dao.deleteById(id);

    }
}
