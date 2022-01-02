package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int productID;

    @Column(name = "product_name", unique = true, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

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

    public Product(int productID, String name, double price, int quantity, String description, String status) {
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.status = status;
    }

    public Product(){
        this.productID = 0;
        this.name = "";
        this.price = 0;
        this.quantity = 0;
        this.description = null;
        this.status = "";
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
        return productID == product.productID && Double.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(status, product.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, name, price, quantity, description, status);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
