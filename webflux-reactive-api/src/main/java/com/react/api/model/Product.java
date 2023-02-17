package com.react.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Product")
public class Product {
    @Id
    private String id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @Size(max = 20)
    private String type;
    
    private BigDecimal price;
    
    private BigDecimal quantity;    
    
    @NotNull
    private Date createdAt = new Date();

}
