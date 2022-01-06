package ro.unibuc.egv.finalProject.models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "accessories")
public class Accessory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long accessoryID;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product accessoryProduct;

    @Column(name = "acc_type", nullable = false)
    private String type;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "compatibility", nullable = false)
    private String compatibility;

    @Column(name = "color", nullable = false)
    private String color;

    public Accessory(Long accessoryID, String type, String brand, String compatibility, String color) {
        this.accessoryID = accessoryID;
        this.type = type;
        this.brand = brand;
        this.compatibility = compatibility;
        this.color = color;
    }

    public Accessory() {

    }

    public Long getAccessoryID() {
        return accessoryID;
    }

    public void setAccessoryID(Long accessoryID) {
        this.accessoryID = accessoryID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Accessory accessory = (Accessory) o;
        return accessoryID != null && Objects.equals(accessoryID, accessory.accessoryID);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "accessoryID = " + accessoryID + ", " +
                "accessoryProduct = " + accessoryProduct + ", " +
                "type = " + type + ", " +
                "brand = " + brand + ", " +
                "compatibility = " + compatibility + ", " +
                "color = " + color + ")";
    }
}
