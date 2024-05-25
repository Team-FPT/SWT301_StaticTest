package com.product.ProductFull.controller;

import com.product.ProductFull.model.Product;
import com.product.ProductFull.service.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestAPI {
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping({"","/"})
    public List<Product> getProducts() {
        return productServiceImp.getAllProducts();
    }





}
