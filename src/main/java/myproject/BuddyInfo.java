package myproject;

import jakarta.persistence.*;

@Entity
@Table(name = "buddy_info")
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;

    // JPA needs a no-arg constructor
    public BuddyInfo() {}

    public BuddyInfo(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // (Optional) copy ctor if you still want it elsewhere, but JPA won’t use it
    public BuddyInfo(BuddyInfo other) {
        this(other.name, other.phoneNumber);
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, phoneNumber);
    }
}
