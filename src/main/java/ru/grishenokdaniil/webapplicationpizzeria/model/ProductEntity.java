package ru.grishenokdaniil.webapplicationpizzeria.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.grishenokdaniil.webapplicationpizzeria.service.ProductType;

@Getter
@Setter
@Entity(name = "Products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    private String productName;
    private String productDescription;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ProductType typeOfProduct;
    private double productPrice;

    public ProductEntity( String productName, String productDescription, ProductType typeOfProduct, double productPrice, String imageUrl) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.typeOfProduct = typeOfProduct;
        this.productPrice = productPrice;
        this.imageUrl= imageUrl;
    }

    public ProductEntity() {

    }

}
