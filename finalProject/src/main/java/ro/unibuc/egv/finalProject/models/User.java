package ro.unibuc.egv.finalProject.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int userID;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "userpass", nullable = false)
    private String password;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "phone_nr", unique = true, nullable = false)
    private String phoneNr;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public User(int userID, String username, String password, String email, String phoneNr, Address address) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNr = phoneNr;
        this.address = address;
    }

    public User(){
        this.userID = 0;
        this.username = "";
        this.password = "";
        this.email = "";
        this.phoneNr = "";
        this.address = null;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(phoneNr, user.phoneNr) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, username, password, email, phoneNr, address);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNr='" + phoneNr + '\'' +
                ", address=" + address +
                '}';
    }
}
