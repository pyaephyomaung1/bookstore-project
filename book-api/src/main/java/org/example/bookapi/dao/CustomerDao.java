package org.example.bookapi.dao;

import org.example.bookapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByUsername(String username);
}
