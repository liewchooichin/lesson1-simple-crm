
package com.pace.app.lesson1_simple_crm;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class ProductController {

  private ArrayList<Product> products = new ArrayList<>();

  public ProductController(){
    products.add(new Product("Chickpeas", "Canned chickpeas", 3.25));
    products.add(new Product("Frozen vegetables", "Easy to use frozen veges", 7.75));
    products.add(new Product("Green apples", "Crunchy green apples", 4.50));
  }

  // create
  @PostMapping("/products")
  public ResponseEntity<Product> createProducts (@RequestBody Product newProduct) {
      //process POST request
      products.add(newProduct);
      return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
  }
  
  // get all
  @GetMapping("/products")
  public ArrayList<Product> getAllProducts() {
      return products;
  }

  // get product info
  private int getProductIndex(String id){
    for(Product product: products){
      if(product.getId().equals(id)){
        return products.indexOf(product);
      }
    }
    // not found
    throw new ProductNotFoundException(id);
  }
  
  // get one product
  @GetMapping("/products/{id}")
  public ResponseEntity<Product> getMethodName(
    @PathVariable String id) {
      try{
        int index = getProductIndex(id);
        Product item = (Product) products.get(index);
        return new ResponseEntity<>(item, HttpStatus.OK);
      } catch(ProductNotFoundException exception){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }

  // update
  @PutMapping("/products/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable String id, 
    @RequestBody Product updatedProduct) {
      //process PUT request
      try{
        int index = getProductIndex(id);
        Product item = products.set(index, updatedProduct);
        Product updatedItem = products.get(index);
        return new ResponseEntity<>(updatedItem, HttpStatus.CREATED);
      } catch (ProductNotFoundException exception){
          products.add(updatedProduct);
          return new ResponseEntity<>(updatedProduct, HttpStatus.CREATED);
      }
  }
  
  // delete
  @DeleteMapping("/products/{id}")
  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable String id){
    // delete
    try{
      int index = getProductIndex(id);
      Product deletedProduct = products.remove(index);
      if(deletedProduct != null)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      else
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);  
    } catch(ProductNotFoundException exception){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

} // end of class