package com.pace.app.lesson1_simple_crm;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceWithLoggingImpl 
  implements CustomerService{
  // fields
  private CustomerRepository customerRepository; 
  private final Logger logger = LoggerFactory.getLogger(
    CustomerServiceWithLoggingImpl.class
  );

  // constructor
  //@Autowired // unnecessary
  public CustomerServiceWithLoggingImpl(CustomerRepository customerRepository){
    this.customerRepository = customerRepository;
  }

  // create
  public Customer createCustomer(Customer customer){
    return customerRepository.creaCustomer(customer);
  }
  // get one
  public Customer getCustomer(String id) {
    return customerRepository.getCustomer(getCustomerIndex(id));
  }
  // get all
  public ArrayList<Customer> getAllCustomers() {
    logger.info("✅ CustomerServiceWithLoggingImpl.getAllCustomers called");
    return customerRepository.getAllCustomers();
  }
  // update
  public Customer updateCustomer(String id, Customer customer) {
    return customerRepository.updateCustomer(getCustomerIndex(id), customer);
  }
  // delete
  public void deleteCustomer(String id) {
    customerRepository.deleteCustomer(getCustomerIndex(id));
  }


  // Helper function
  private int getCustomerIndex(String id) {
    ArrayList<Customer> customers = customerRepository.getAllCustomers();
    for (Customer customer : customers) {
      if (customer.getId().equals(id)) {
        return customers.indexOf(customer);
      }
    }
    // Not found
    // return -1;
    throw new CustomerNotFoundException(id);
  }

} // end of class
