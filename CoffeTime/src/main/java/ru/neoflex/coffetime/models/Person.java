package ru.neoflex.coffetime.models;

import lombok.Data;
import org.springframework.stereotype.Component;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
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

}
