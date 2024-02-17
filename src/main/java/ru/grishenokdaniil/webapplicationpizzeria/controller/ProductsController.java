package ru.grishenokdaniil.webapplicationpizzeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.grishenokdaniil.webapplicationpizzeria.model.ProductEntity;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductType;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductsController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductsController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @ModelAttribute("productTypes")
    public ProductType[] productTypes() {
        return ProductType.values();
    }

    @GetMapping("/admin/product")
    public String showProducts(Model model) {
        List<ProductEntity> products = productService.getAllProducts();
        model.addAttribute("products", products);

        return "product";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Optional<ProductEntity> productOptional = productService.getProductById(id);
        productOptional.ifPresent(product -> model.addAttribute("product", product));

        return "editProduct";
    }


    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute ProductEntity updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/admin/product";
    }
    @PostMapping("/admin/product")
    public String addProduct(@ModelAttribute("product") ProductEntity product,
                             Model model) {
        productRepository.save(product);

        return "redirect:/admin/product";
    }



}