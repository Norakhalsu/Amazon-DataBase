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
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @NotEmpty(message = " Merchant Name have not be Empty ")
    @Size(min = 4 ,max = 15 ,message = " Merchant Name must be more than 3 character ")
    @Column(columnDefinition ="varchar(15)", unique = true, nullable = false)
    private String name;


}
