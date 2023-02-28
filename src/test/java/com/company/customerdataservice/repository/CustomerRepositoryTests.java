package com.company.customerdataservice.repository;

import com.company.customerdataservice.models.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepo;

    @Before
    public void SetUpo() throws Exception {
        customerRepo.deleteAll();
    }

    @Test
    public void ShouldCreateACustomer () {
        Customer customer = new Customer();

        customer.setFirstName("Dalonte");
        customer.setLastName("Obmam");
        customer.setEmail("Netflix@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1237 Gamer Street");
        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("12356");
        customer.setCountry("USA");

        customer = customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(), customer);
    }


    @Test
    public void ShouldUpdateACustomer(){ //could be broken with the equals stuff
        Customer customer = new Customer();

        customer.setFirstName("Dalonte");
        customer.setLastName("Obama");
        customer.setEmail("Netflix@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1237 Gamer Street");
        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("12356");
        customer.setCountry("USA");

        customer = customerRepo.save(customer);

        customer.setFirstName("Trey");

        customer = customerRepo.save(customer);

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());

        assertEquals(customer1.get(),customer); // ensures updated customer does not match orignal submission
    }

    @Test
    public void ShouldDeleteACustomer() {
        Customer customer = new Customer();

        customer.setFirstName("Dalonte");
        customer.setLastName("Obama");
        customer.setEmail("Netflix@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1237 Gamer Street");
        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("12356");
        customer.setCountry("USA");

        customerRepo.save(customer);

        customerRepo.deleteById(customer.getId());

        Optional<Customer> customer1 = customerRepo.findById(customer.getId());
        assertFalse(customer1.isPresent());
    }

    @Test
    public void ShouldFindACustomerById() {
        Customer customer = new Customer();

        customer.setFirstName("Dalonte");
        customer.setLastName("Obama");
        customer.setEmail("Netflix@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1237 Gamer Street");
        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("12356");
        customer.setCountry("USA");

        customer = customerRepo.save(customer);


        Optional<Customer> customer1 = customerRepo.findById(customer.getId());


        assertEquals(customer1.get(), customer);

    }

    //Test custom method
    @Test
    public void ShouldFindACustomerByState() {
        Customer customer = new Customer();

        customer.setFirstName("Dalonte");
        customer.setLastName("Obama");
        customer.setEmail("Netflix@gmail.com");
        customer.setCompany("Netflix");
        customer.setPhone("123-456-7890");
        customer.setAddress1("1237 Gamer Street");
        customer.setCity("New York");
        customer.setState("New York");
        customer.setPostalCode("12356");
        customer.setCountry("USA");

        customer = customerRepo.save(customer);

        List<Customer> customers = customerRepo.findAllByState("New York");

        assertEquals(customers.get(0), customer);

    }

}
