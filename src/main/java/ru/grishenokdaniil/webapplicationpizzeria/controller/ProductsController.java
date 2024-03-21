package ru.grishenokdaniil.webapplicationpizzeria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.grishenokdaniil.webapplicationpizzeria.service.ImageService;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductService;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.ProductType;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ImageService imageService;



    @ModelAttribute("productTypes")
    public ProductType[] productTypes() {
        return ProductType.values();
    }

    @GetMapping("/admin/product")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/product";
    }
    @GetMapping("/admin/product/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Optional<Product> productOptional = productService.getProductById(id);
        productOptional.ifPresent(product -> model.addAttribute("product", product));

            return "editProduct";
    }


    @PostMapping("/admin/product/edit/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/admin/product";
    }
    @PostMapping("/admin/product")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("productImage") MultipartFile productImage,
                             Model model) throws IOException {
        if (!productImage.isEmpty()) {
            String imageUrl = imageService.upload(productImage);
            product.setImageUrl(imageUrl);
        }
        productRepository.save(product);

        return "redirect:/admin/product";
    }




}