package myproject;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** AddressBook that has-a list of BuddyInfo objects. */
@Entity
@Table(name = "address_book")
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unidirectional one-to-many. Creates FK buddy_info.addressbook_id */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "addressbook_id")  // FK lives in buddy_info table
    private List<BuddyInfo> buddies = new ArrayList<>();

    public AddressBook() {}

    /** --- NEW: expose id for Thymeleaf/JSON --- */
    public Long getId() {
        return id;
    }

    /** Add a BuddyInfo (null is ignored). Do NOT defensive-copy with JPA. */
    public void addBuddy(BuddyInfo buddy) {
        if (buddy != null) {
            buddies.add(buddy);
        }
    }

    /** Remove the first buddy with the given name. Returns true if removed. */
    public boolean removeBuddyByName(String name) {
        if (name == null) return false;
        for (int i = 0; i < buddies.size(); i++) {
            if (name.equals(buddies.get(i).getName())) {
                buddies.remove(i);
                return true;
            }
        }
        return false;
    }

    /** --- NEW: remove by entity (used when you loaded a BuddyInfo by id) --- */
    public boolean removeBuddy(BuddyInfo buddy) {
        if (buddy == null) return false;
        return buddies.remove(buddy);
    }

    /** --- NEW: convenience to remove by buddy id --- */
    public boolean removeBuddyById(Long buddyId) {
        if (buddyId == null) return false;
        for (int i = 0; i < buddies.size(); i++) {
            BuddyInfo b = buddies.get(i);
            if (buddyId.equals(b.getId())) {
                buddies.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<BuddyInfo> getBuddies() {
        return Collections.unmodifiableList(buddies);
    }

    /** Find the first buddy with this name or null. */
    public BuddyInfo findByName(String name) {
        if (name == null) return null;
        for (BuddyInfo b : buddies) {
            if (name.equals(b.getName())) {
                return b; // return the managed entity (no defensive copy)
            }
        }
        return null;
    }

    /** Remove all buddies. */
    public void clear() {
        buddies.clear();
    }

    /** Current number of buddies. */
    public int size() {
        return buddies.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AddressBook:\n");
        for (BuddyInfo b : buddies) {
            sb.append(" - ").append(b).append('\n');
        }
        return sb.toString();
    }

    /** Demo without JPA (still fine to keep) */
    public static void main(String[] args) {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("Ibrahim", "613-700-4250"));
        book.addBuddy(new BuddyInfo("saad", "613-413-1061"));
        System.out.println(book);
    }
}
