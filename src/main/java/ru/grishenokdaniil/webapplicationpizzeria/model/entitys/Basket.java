package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import lombok.Data;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.BasketItem;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Entity(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketItem> basketItems = new ArrayList<>();

    private LocalDateTime dateOfCreated;

    @PrePersist
    public void init() {
        dateOfCreated = LocalDateTime.now();
    }

    public void addItem(Product product) {
       Optional<BasketItem> existingItemOptional = basketItems.stream()
               .filter(item ->item.getProduct().getProductID().equals(product.getProductID()))
               .findFirst();
        if (existingItemOptional.isPresent()) {
            BasketItem existingItem = existingItemOptional.get();
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            BasketItem basketItem = new BasketItem();
            basketItem.setProduct(product);
            basketItem.setBasket(this);
            basketItem.setQuantity(1);
            basketItems.add(basketItem);
        }
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (BasketItem item : basketItems) {
            products.add(item.getProduct());
        }
        return products;
    }
    public void removeProductById(Long productId) {
        Optional<BasketItem> itemToRemove = basketItems.stream()
                .filter(item -> item.getProduct().getProductID().equals(productId))
                .findFirst();
        itemToRemove.ifPresent(item -> {
            int newQuantity = item.getQuantity() - 1;
            if (newQuantity <= 0) {
                basketItems.remove(item);
            } else {
                item.setQuantity(newQuantity);
            }
        });
    }
}
