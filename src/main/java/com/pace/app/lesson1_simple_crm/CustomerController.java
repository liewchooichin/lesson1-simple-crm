package com.pace.app.lesson1_simple_crm;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  // fields
  private ArrayList<Customer> customers = new ArrayList<>();

  // add some customers
  // constructor of the controller's class
  public CustomerController() {
    customers.add(new Customer("Super", "Dog"));
    customers.add(new Customer("Magnifient", "Cat"));
    customers.add(new Customer("Fantastic", "Fox"));
    customers.add(new Customer("Free", "Eagle"));
  }

  // create
  // @PostMapping("/customers")
  @PostMapping("")
  public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
    customers.add(newCustomer);
    return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
  }

  // get all customers
  // @GetMapping("/customers")
  @GetMapping("")
  public ResponseEntity<ArrayList<Customer>> getAllCustomers() {
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  // get one customer
  private int getCustomerIndex(String id) {
    for (Customer customer : customers) {
      if (customer.getId().equals(id)) {
        return customers.indexOf(customer);
      }
    }
    // Not found
    // return -1;
    throw new CustomerNotFoundException(id);
  }

  // @GetMapping("/customers/{id}")
  @GetMapping("/{id}")
  public ResponseEntity<Customer> getCustomer(@PathVariable String id) {
    try {
      int index = getCustomerIndex(id);
      Customer item = (Customer) customers.get(index);
      return new ResponseEntity<>(item, HttpStatus.OK);
    } catch (CustomerNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // update
  // @PutMapping("/customers/{id}")
  @PutMapping("/{id}")
  public ResponseEntity<Customer> updateCustomer(
      @PathVariable String id, @RequestBody Customer customer) {

    try {
      int index = getCustomerIndex(id);
      customers.set(index, customer);
      Customer updatedCustomer = customers.get(index);
      return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
    } catch (CustomerNotFoundException exception) {
      // add a new customer
      customers.add(customer);
      return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
  }

  // delete
  // @DeleteMapping("/customers/{id}")
  @DeleteMapping("/{id}")
  // public Customer deleteCustomer(@PathVariable String id) {
  public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String id) {
    try {
      int index = getCustomerIndex(id);
      customers.remove(index);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (CustomerNotFoundException exception) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // Test page
  @GetMapping("/hello")
  public String hello() {
    return new String("Hello Live Page");
  } // end hello
} // end class
