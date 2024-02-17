package ru.grishenokdaniil.webapplicationpizzeria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grishenokdaniil.webapplicationpizzeria.model.ProductEntity;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getAllProducts() {
        return (List<ProductEntity>) productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void updateProduct(Long id, ProductEntity updatedProduct) {
        Optional<ProductEntity> existingProductOptional = productRepository.findById(id);

        existingProductOptional.ifPresent(existingProduct -> {
            // Обновляем данные продукта
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setTypeOfProduct(updatedProduct.getTypeOfProduct());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());

            // Сохраняем обновленный продукт в репозиторий
            productRepository.save(existingProduct);
        });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void addProduct(ProductEntity product) {
        productRepository.save(product);
    }
}
