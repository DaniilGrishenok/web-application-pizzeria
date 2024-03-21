package ru.grishenokdaniil.webapplicationpizzeria.model.entitys;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.grishenokdaniil.webapplicationpizzeria.model.enams.ProductType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String productDescription;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ProductType typeOfProduct;
    private double productPrice;
    private LocalDateTime dateOfCreated;
    public Product(String productName, String productDescription, ProductType typeOfProduct, double productPrice, String imageUrl) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.typeOfProduct = typeOfProduct;
        this.productPrice = productPrice;
        this.imageUrl= imageUrl;
    }

    @PrePersist
    public void init(){
        dateOfCreated = LocalDateTime.now();
    }
    public Product() {

    }

}
