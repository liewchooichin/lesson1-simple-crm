package com.pace.app.lesson1_simple_crm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
public class CustomerController {
  
  // get the customer pojo class
  @Autowired
  private Customer customer;

  // get customer
  @GetMapping("/customer/{customerId}")
  public Customer getCustomer(@PathVariable String customerId) {
      customer.setId(customerId);
      customer.setFirstName("Mary");
      customer.setLastName("Maven");
      customer.setEmail("marymaven@email.com");
      customer.setContactNo("1234-1234");
      customer.setJobTitle("Software Developer");
      customer.setYearOfBirth(1980);
      return customer;
  }
  

}
