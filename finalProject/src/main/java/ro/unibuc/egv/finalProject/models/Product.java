package ro.unibuc.egv.finalProject.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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

    @OneToOne(mappedBy = "consoleProduct")
    private Console console;

    @OneToOne(mappedBy = "gameProduct")
    private Game game;

    @OneToOne(mappedBy = "accessoryProduct")
    private Accessory accessory;

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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return productID != null && Objects.equals(productID, product.productID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "productID = " + productID + ", " +
                "name = " + name + ", " +
                "price = " + price + ", " +
                "quantity = " + quantity + ", " +
                "description = " + description + ", " +
                "status = " + status + ", " +
                "console = " + console + ", " +
                "game = " + game + ", " +
                "accessory = " + accessory + ")";
    }
}
