package com.product.ProductFull.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    @NotEmpty(message = "The name is required")
    private String name;
    @Min(0)
    private double price;

    private MultipartFile img;
}
