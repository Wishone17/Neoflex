package ru.neoflex.coffetime.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class Person {

    private int id;

    @NotEmpty(message = "The line must be filled")
    @Size(min=2, max=20, message = "The entered value is out of range(2 - 20)")
    private String name;

    @NotEmpty(message = "The line must be filled")
    @Size(min=2, max=20, message = "The entered value is out of range(2 - 30)")
    private String surname;

    @NotEmpty(message = "The line must be filled")
    @Email(message = "The entered value does not match the email")
    private String email;

   public Person(int id, String name, String surname, String email) {
       this.id = id;
       this.name = name;
       this.surname = surname;
       this.email = email;
   }

   public Person() {
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public String getSurname() {
       return surname;
   }

   public void setSurname(String surname) {
       this.surname = surname;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }
}
