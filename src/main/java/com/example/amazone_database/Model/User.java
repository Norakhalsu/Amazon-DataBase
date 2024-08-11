package com.example.amazone_database.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "User Name must not be Empty ")
    @Size(min = 6,max = 12,message = " User Name must be more than 5 character ")
    @Column(columnDefinition = "varchar(12)",unique = true, nullable = false)
    private String username;


    @NotEmpty(message = "User Password must not be Empty ")
    @Size(min = 7 ,max = 20, message = " User Password must be between 6-20 characters ")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$", message = "Password must contain at least one letter and one digit (no Symbols) ")
    @Column(columnDefinition = "varchar(20)",nullable = false, unique = true)
    private String password;


    @NotEmpty(message = "User Email must not be Empty ")
    @Email(message = "User Email must be in Email Format ")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @Size(min = 5,max = 8 ,message = " Role length must be 5-10 character ")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    @NotEmpty(message = " User Role must not be Empty ")
   // @Column(columnDefinition = "varchar(8) not null check(role='Admin' or role='Customer' ) ")
    private String role;

    @NotNull(message = " User Balance must be not empty ")
    @Positive(message = "User Balance must be a Positive ")
    @Min(value = 0)
    @Column(columnDefinition = "double not null")
    private double balance;

    private int score;


}
