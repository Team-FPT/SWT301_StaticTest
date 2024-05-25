package com.product.ProductFull.controller;

import com.product.ProductFull.model.*;
import com.product.ProductFull.service.ProductServiceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.*;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping({"", "/"})
    public String getAll(Model model) {
//        List<Product> ls = productServiceImp.getAllProducts();
        RestTemplate restTemplate = new RestTemplate();
        Product[] ls = restTemplate.getForObject("http://localhost:8080/api", Product[].class);
        for(Product p : ls){
            System.out.println(p.getName());
        }
        model.addAttribute("products", ls);
        return "product/index";
    }

    @GetMapping("/create")
    public String showPageCreate(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "product/create";
    }

    @PostMapping("create")
    public String createProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result) {

        if (productDto.getImg().isEmpty()) {
            result.addError(new FieldError("productDto", "img", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "product/create";
        }

        // save image file
        MultipartFile image = productDto.getImg();
        String storageFileName = image.getOriginalFilename();
        try {
            String uploadDir = "public/img/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Product p = new Product();
        p.setName(productDto.getName());
        p.setPrice(productDto.getPrice());
        p.setImg(storageFileName);
        productServiceImp.addProduct(p);
        return "redirect:/product";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {

        try {
            Product p = productServiceImp.getProductById(id);


            ProductDto pDto = new ProductDto();
            pDto.setName(p.getName());
            pDto.setPrice(p.getPrice());
            model.addAttribute("productDto", pDto);
            model.addAttribute("product", p);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/product";
        }


        return "product/editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute ProductDto productDto, BindingResult result, Model model, @RequestParam int id) {


        try {
            Product p = productServiceImp.getProductById(id);
            model.addAttribute("product", p);
            if (result.hasErrors()) {
                return "product/editProduct";
            }
            MultipartFile image = productDto.getImg();
            if (!image.isEmpty()) {
                //delete old image
                String uploadDir = "public/img/";
                Path oldImagePath = Paths.get(uploadDir + p.getImg());
                try {
                    Files.delete(oldImagePath);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                //save new image file

                String storageFileName = image.getOriginalFilename();
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                p.setImg(storageFileName);

            }
            p.setName(productDto.getName());
            p.setPrice(productDto.getPrice());
            productServiceImp.updateProduct(id, p);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return "redirect:/product";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int id) {
        productServiceImp.deleteProduct(id);
        return "redirect:/product";
    }

}
