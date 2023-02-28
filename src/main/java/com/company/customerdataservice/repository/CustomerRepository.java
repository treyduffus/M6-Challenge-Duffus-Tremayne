package com.company.customerdataservice.repository;

import com.company.customerdataservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByState(String state); //custom query to select from customer table by state


}
