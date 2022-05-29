package com.example.trych.controller;

import com.example.trych.dao.CustomerDao;
import com.example.trych.dto.CustomerDto;
import com.example.trych.model.Customer;
import com.example.trych.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @Autowired
    CustomerDao dao;

    @GetMapping
    public String showCustomerPage(){
        return "customer/customerPage";
    }

    @GetMapping("/getall")
    public String showCustomerGetAllPage(Model model){
        model.addAttribute("customers", service.getAll());
        return "customer/getAllPage";
    }

    @GetMapping("/getone")
    public String showCustomerGetOnePage(Model model, Long idgetone){
        model.addAttribute("customer", service.getOne(idgetone));

        String title = "Busca de Cliente";
        model.addAttribute("title", title);

        String errormessage = "Cliente não encontrado";
        model.addAttribute("errormessage", errormessage);
        String message = "";
        model.addAttribute("message", message);

        return "customer/getOnePage";
    }

    @GetMapping("/registerform")
    public String showCustomerRegisterFormPage(Model model){
        Customer newCustomer = new Customer();
        model.addAttribute("customer", newCustomer);
        return "customer/registerFormPage";
    }

    @PostMapping("/register")
    public String registerCustomer(Model model, Customer newcustomer){

        model.addAttribute("customer", service.save(newcustomer));

        String title = "Cadastro de cliente";
        model.addAttribute("title", title);

        String errormessage = "Não foi possível cadastrar o cliente";
        model.addAttribute("errormessage", errormessage);
        String message = "Cliente cadastrado com sucesso.";
        model.addAttribute("message", message);
        return "customer/getOnePage";
    }




    @GetMapping("/delete/{id}")
    public String excluir(@PathVariable("id") long id, Model model){
        String message;
        Optional<Customer> customerOptional = dao.findById(id);
        if (customerOptional.isEmpty()){
            message = "Cliente não encontrado";
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        dao.delete(customerOptional.get());
        message = "Cliente apagado com sucesso";
        model.addAttribute("message", message);

        return "customer/messagePage";
    }


    @GetMapping("/updateform/{id}")
    public String updateCustomerForm(@PathVariable("id") long id, Model model){
        Optional<Customer> customerOptional = dao.findById(id);
        if (customerOptional.isEmpty()){
            throw new IllegalArgumentException("Cliente não encontrado.");
        }
        model.addAttribute("customer", customerOptional.get());
        return "customer/updateFormPage";
    }


    @PostMapping("/update")
    public String updateCustomer(Model model, Customer customerToUpdate){
        service.update(customerToUpdate);

        model.addAttribute("customer", customerToUpdate);

        String title = "Alteração de cliente";
        model.addAttribute("title", title);

        String errormessage = "Não foi possível alterar o cliente";
        model.addAttribute("errormessage", errormessage);
        String message = "Cliente alterado com sucesso.";
        model.addAttribute("message", message);
        return "customer/getOnePage";
    }




}
