package com.company.customerdataservice.controllers;

import com.company.customerdataservice.models.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository repo;


    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception{

    }


    @Test
    public void shouldCreateCustomer() throws Exception {

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


        String inputJson = mapper.writeValueAsString(customer);

        mockMvc.perform(post("/customer").content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }


    @Test
    public void shouldUpdateACustomer () throws Exception {

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

        String inputJson = mapper.writeValueAsString(customer);

        mockMvc.perform(
                        put("/customer")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteACustomer () throws Exception {

        mockMvc.perform(delete("/customer/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldGetACustomerById () throws Exception {


        mockMvc.perform(get("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetACustomerByState () throws Exception {

        mockMvc.perform(get("/customer/state/Florida"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
