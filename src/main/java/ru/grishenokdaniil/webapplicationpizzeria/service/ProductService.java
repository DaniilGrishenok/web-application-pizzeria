package ru.grishenokdaniil.webapplicationpizzeria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Basket;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.BasketItem;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.Product;
import ru.grishenokdaniil.webapplicationpizzeria.repository.BasketRepository;
import ru.grishenokdaniil.webapplicationpizzeria.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;


    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(id);

        existingProductOptional.ifPresent(existingProduct -> {
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setTypeOfProduct(updatedProduct.getTypeOfProduct());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            productRepository.save(existingProduct);
        });
    }

    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            List<Basket> baskets = basketRepository.findByBasketItemsProduct(product);
            for (Basket basket : baskets) {
                List<BasketItem> basketItems = basket.getBasketItems();
                basketItems.removeIf(item -> item.getProduct().equals(product));
                basketRepository.save(basket);
            }
            productRepository.delete(product);
        }
    }
    public double calculateTotalAmount(List<BasketItem> basketItems) {
        double totalAmount = 0.0;
        for (BasketItem item : basketItems) {
            totalAmount += item.product.getProductPrice()* item.getQuantity();
        }
        return totalAmount;
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
