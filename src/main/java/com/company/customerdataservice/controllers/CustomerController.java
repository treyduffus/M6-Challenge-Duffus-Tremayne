package com.company.customerdataservice.controllers;

import com.company.customerdataservice.models.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;


    //Create a new customer record.
    @PostMapping("/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){ //works
        return repo.save(customer);
    }

    //Update an existing customer record.
    @PutMapping("/customer")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@RequestBody Customer customer){  //works but you have to pass an id along with the whole object
        repo.save(customer);
    }

    //Delete an existing customer record.
    @DeleteMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id){ //works but you have to pass an id along with the whole object
        repo.deleteById(id);
    }

    //Find a customer record by id.
    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerByid(@PathVariable int id){ //works

        Optional<Customer> returnVal = repo.findById(id); // The find by id method returns an optional object
        if (returnVal.isPresent()) {
            return returnVal.get(); //If the customer exists
        } else {
            return null; //else return a null object
        }
    }

    //Find customer records by state.
    @GetMapping("/customer/state/{state}")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> findCustomersByState(@PathVariable String state){ // works
        return repo.findAllByState(state);
    }

}
