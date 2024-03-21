package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import lombok.Data;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.BasketItem;
import ru.grishenokdaniil.webapplicationpizzeria.model.entitys.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        if (basketItems == null) {
            basketItems = new ArrayList<>();
        }

        BasketItem basketItem = new BasketItem();
        basketItem.setProduct(product);
        basketItem.setBasket(this);

        basketItems.add(basketItem);
    }

    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (BasketItem item : basketItems) {
            products.add(item.getProduct());
        }
        return products;
    }
}
