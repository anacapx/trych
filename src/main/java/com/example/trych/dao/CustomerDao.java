package com.example.trych.dao;

import com.example.trych.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Long> {
}
