package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItem_id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private double unit_price;

    private LocalDateTime dateAdded;
    @PrePersist
    public void init() {
        dateAdded = LocalDateTime.now();
    }
}
