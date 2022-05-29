package com.example.trych.dto;

import com.example.trych.model.Customer;
import lombok.*;


import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private Long id;
    private String email;
    private String name;
    private String cpf;
    private String cep;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.email = customer.getEmail();
        this.name = customer.getName();
        this.cpf = customer.getCpf();
        this.cep = customer.getCep();
    }

//    public static CustomerDto convertCustomerToDto(Customer customer){
//        return new CustomerDto(customer.getId(), customer.getEmail(), customer.getName(), customer.getCpf(), customer.getCep());
//    }

    public static List<CustomerDto> converter(List<Customer> customers){
        return customers.stream().map(CustomerDto::new).collect(Collectors.toList());
    }
}
