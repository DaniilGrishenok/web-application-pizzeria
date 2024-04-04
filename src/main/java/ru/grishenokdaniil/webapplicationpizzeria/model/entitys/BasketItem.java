package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "basket_items")
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long basketItemId;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product product;

    private int quantity;

    private LocalDateTime dateAdded;

    @PrePersist
    public void init() {
        dateAdded = LocalDateTime.now();
    }
}
