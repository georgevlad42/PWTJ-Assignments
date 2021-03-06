package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long productID;

    @Column(name = "product_name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "product_desc")
    private String description;

    @Column(name = "product_status", nullable = false)
    private String status;

    @OneToOne(mappedBy = "product")
    private Console console;

    @OneToOne(mappedBy = "product")
    private Game game;

    @OneToOne(mappedBy = "product")
    private Accessory accessory;

    public Product(String name, Double price, Integer quantity, String description, String status) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public Product(Long productID, String name, Double price, Integer quantity, String description, String status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public Product(){

    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) && Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity) && Objects.equals(description, product.description) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, price, quantity, description, status);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "productID = " + productID + ", " +
                "name = " + name + ", " +
                "price = " + price + ", " +
                "quantity = " + quantity + ", " +
                "description = " + description + ", " +
                "status = " + status + ")";
    }
}
