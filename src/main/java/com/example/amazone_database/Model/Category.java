package com.example.amazone_database.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4 , max = 15,message = " Category Name must more than 3 character ")
    @NotEmpty(message = " Category Name must not be empty ")
    @Column(columnDefinition = "varchar(15)",unique = true,nullable = false)
    private String name;


}
