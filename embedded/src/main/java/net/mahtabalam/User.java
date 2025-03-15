package net.mahtabalam;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String email;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "addressLine1", column = @Column(name = "address_line_1")),
            @AttributeOverride(name = "addressLine2", column = @Column(name = "address_line_2"))
    })
    private Address address;

    public User(String email) {
        this.email = email;
    }

    public User(String email, Address address) {
        this.email = email;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }
}
