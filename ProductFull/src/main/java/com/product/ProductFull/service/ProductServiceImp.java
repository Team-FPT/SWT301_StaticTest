package com.product.ProductFull.service;

import com.product.ProductFull.model.Product;
import com.product.ProductFull.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductServiceImp implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(int id, Product product) {
        Product product1 = productRepository.findById(id).get();
        product1.setName(product.getName());
        product1.setImg(product.getImg());
        product1.setPrice(product.getPrice());
        productRepository.save(product1);
        return product1;
    }

    @Override
    public Product deleteProduct(int id) {
        Product product = productRepository.findById(id).get();
        productRepository.deleteById(id);
        return product;
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
