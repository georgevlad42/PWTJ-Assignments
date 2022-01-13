package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@SuppressWarnings("unused")
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long addressID;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "nr", nullable = false)
    private Integer number;

    @Column(name = "building")
    private String building;

    @Column(name = "entrance")
    private String entrance;

    @Column(name = "floor")
    private Integer floor;

    @Column(name = "apartment")
    private Integer apartment;

    @Column(name = "interphone")
    private String interphone;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address(String country, String district, String city, String street, Integer number, String building, String entrance, Integer floor, Integer apartment, String interphone, String postalCode) {
        this.country = country;
        this.district = district;
        this.city = city;
        this.street = street;
        this.number = number;
        this.building = building;
        this.entrance = entrance;
        this.floor = floor;
        this.apartment = apartment;
        this.interphone = interphone;
        this.postalCode = postalCode;
    }

    public Address(Long addressID, String country, String district, String city, String street, Integer number, String building, String entrance, Integer floor, Integer apartment, String interphone, String postalCode) {
        this.addressID = addressID;
        this.country = country;
        this.district = district;
        this.city = city;
        this.street = street;
        this.number = number;
        this.building = building;
        this.entrance = entrance;
        this.floor = floor;
        this.apartment = apartment;
        this.interphone = interphone;
        this.postalCode = postalCode;
    }

    public Address(){

    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public String getInterphone() {
        return interphone;
    }

    public void setInterphone(String interphone) {
        this.interphone = interphone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(addressID, address.addressID) && Objects.equals(country, address.country) && Objects.equals(district, address.district) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(number, address.number) && Objects.equals(building, address.building) && Objects.equals(entrance, address.entrance) && Objects.equals(floor, address.floor) && Objects.equals(apartment, address.apartment) && Objects.equals(interphone, address.interphone) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressID, country, district, city, street, number, building, entrance, floor, apartment, interphone, postalCode);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "addressID = " + addressID + ", " +
                "country = " + country + ", " +
                "district = " + district + ", " +
                "city = " + city + ", " +
                "street = " + street + ", " +
                "number = " + number + ", " +
                "building = " + building + ", " +
                "entrance = " + entrance + ", " +
                "floor = " + floor + ", " +
                "apartment = " + apartment + ", " +
                "interphone = " + interphone + ", " +
                "postalCode = " + postalCode + ")";
    }
}
