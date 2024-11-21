package com.pace.app.lesson1_simple_crm;

public class ProductNotFoundException 
extends RuntimeException {
  ProductNotFoundException(String id) {
    super("Could not find product with id: " + id);
  }
  
}
