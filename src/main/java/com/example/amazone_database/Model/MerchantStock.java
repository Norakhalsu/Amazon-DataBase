package com.example.amazone_database.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class MerchantStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = " Product Id must not be Empty ")
    @Column(columnDefinition = "int not null")
    private Integer productId;

    @NotNull(message = " Merchant Id must not be Null ")
    @Column(columnDefinition = "int not null")
    private Integer merchantId;

    @NotNull(message = " Stock must not be Null ")
    @Min(value = 11 , message = "  Stock have to be more than 10 at start ")
    @Column(columnDefinition = "int not null")
    private int stock;


}
