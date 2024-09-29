package ru.semavin.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {
    private int id ;
    @NotEmpty(message = "Username is required")
    @Size(min = 0, max = 15, message = "Username must be 0 < username < 15")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Pass must be at least 6 characters long")
    private String pass;
    private long balance;
}
