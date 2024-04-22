package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import lombok.Getter;
import lombok.Setter;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private double totalSum;
    private String name;
    private String numberPhone;
    private String email;
    private String street;
    private String house;
    private String flat;
    private String descriptionForCourier;

    private LocalDateTime dateOfCreate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @PrePersist
    private void init() {
        dateOfCreate = LocalDateTime.now();
        status = OrderStatus.AwaitingPayment;
    }

    public void addItem(BasketItem basketItem) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(basketItem.getProduct());
        orderItem.setOrder(this);
        orderItem.setQuantity(basketItem.getQuantity());
        orderItem.setUnit_price(basketItem.getProduct().getProductPrice());
        orderItems.add(orderItem);
    }

    public double getTotalPrice() {
        return orderItems.stream()
                .mapToDouble(orderItem -> orderItem.getUnit_price() * orderItem.getQuantity())
                .sum();
    }
}
