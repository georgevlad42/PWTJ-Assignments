package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int addressID;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "nr", nullable = false)
    private int number;

    @Column(name = "building")
    private String building;

    @Column(name = "entrance")
    private String entrance;

    @Column(name = "floor")
    private int floor;

    @Column(name = "apartment")
    private int apartment;

    @Column(name = "interphone")
    private String interphone;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @OneToOne(mappedBy = "address")
    private User user;

    public Address(int addressID, String country, String district, String city, String street, int number, String building, String entrance, int floor, int apartment, String interphone, String postalCode) {
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
        this.addressID = 0;
        this.country = "";
        this.district = "";
        this.city = "";
        this.street = "";
        this.number = 0;
        this.building = null;
        this.entrance = null;
        this.floor = -1;
        this.apartment = -1;
        this.interphone = null;
        this.postalCode = "";
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getApartment() {
        return apartment;
    }

    public void setApartment(int apartment) {
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
        return addressID == address.addressID && number == address.number && floor == address.floor && apartment == address.apartment && Objects.equals(country, address.country) && Objects.equals(district, address.district) && Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(building, address.building) && Objects.equals(entrance, address.entrance) && Objects.equals(interphone, address.interphone) && Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressID, country, district, city, street, number, building, entrance, floor, apartment, interphone, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressID=" + addressID +
                ", country='" + country + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", number=" + number +
                ", building='" + building + '\'' +
                ", entrance='" + entrance + '\'' +
                ", floor=" + floor +
                ", apartment=" + apartment +
                ", interphone='" + interphone + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
