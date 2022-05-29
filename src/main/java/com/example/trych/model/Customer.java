package com.example.trych.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "customer_tb")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank(message = "O campo 'Nome' é obrigatório.")
    @Column(name = "customer_name")
    private String name;

    @NotBlank(message = "O campo 'Email' é obrigatório.")
    @Column(name = "customer_email")
    private String email;

    @NotBlank(message = "O campo 'CPF' é obrigatório.")
    @Column(name = "customer_cpf")
    private String cpf;

    @NotBlank(message = "O campo 'CEP' é obrigatório.")
    @Column(name = "customer_cep")
    private String cep;

    @Column(name = "customer_password")
    private String password;

    public Customer(String name, String email, String cpf, String cep) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.cep = cep;
    }

//    @OneToMany(mappedBy = "customer")
//    @JsonIgnoreProperties("customer")
//    private List<Order> orderList;
}
