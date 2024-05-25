package com.product.ProductFull.service;

import com.product.ProductFull.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    public List<Product> getAllProducts();
    public Product getProductById(int id);
    public Product updateProduct(int id, Product product);
    public Product deleteProduct(int id);
    public void addProduct(Product product);
}
