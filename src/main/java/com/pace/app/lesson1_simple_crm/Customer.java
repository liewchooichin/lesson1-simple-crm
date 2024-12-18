package com.pace.app.lesson1_simple_crm;

import java.util.UUID;

public class Customer {
private final String id;
private String firstName;
private String lastName;
private String email;
private String contactNo;
private String jobTitle;
private int yearOfBirth;

// default
public Customer(){
  this.id = UUID.randomUUID().toString();
} 

public Customer(String id, String firstName, String lastName, String email, String contactNo, String jobTitle,
    int yearOfBirth) {
  this.id = UUID.randomUUID().toString();
  this.firstName = firstName;
  this.lastName = lastName;
  this.email = email;
  this.contactNo = contactNo;
  this.jobTitle = jobTitle;
  this.yearOfBirth = yearOfBirth;
}

public Customer(String firstName, String lastName){
  this.id = UUID.randomUUID().toString();
  this.firstName = firstName;
  this.lastName = lastName;
}

public String getId() {
  return id;
}

public String getFirstName() {
  return firstName;
}

public void setFirstName(String firstName) {
  this.firstName = firstName;
}

public String getLastName() {
  return lastName;
}

public void setLastName(String lastName) {
  this.lastName = lastName;
}

public String getEmail() {
  return email;
}

public void setEmail(String email) {
  this.email = email;
}

public String getContactNo() {
  return contactNo;
}

public void setContactNo(String contactNo) {
  this.contactNo = contactNo;
}

public String getJobTitle() {
  return jobTitle;
}

public void setJobTitle(String jobTitle) {
  this.jobTitle = jobTitle;
}

public int getYearOfBirth() {
  return yearOfBirth;
}

public void setYearOfBirth(int yearOfBirth) {
  this.yearOfBirth = yearOfBirth;
}



}
