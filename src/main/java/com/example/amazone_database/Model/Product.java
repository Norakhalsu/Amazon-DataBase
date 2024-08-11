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
public class Product {


    //@NotNull(message = " Product id must not be null ")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = " Product Name must not be  Empty ")
    @Size(min = 4, max = 15, message = "Product Name must be more than 3 character ")
    @Column(columnDefinition = "varchar(15)",unique = true, nullable = false)
    private String productName;

    @NotNull(message = " Price must not be Null ")
    @PositiveOrZero(message = "Price must be positive number ")
    @Min(value = 0)
    @Column(columnDefinition = "double not null")
    private double price;

    @NotNull(message = " Category Id must not be Null  ")
    @Column(columnDefinition = "int not null ")
    private Integer categoryId;

}
