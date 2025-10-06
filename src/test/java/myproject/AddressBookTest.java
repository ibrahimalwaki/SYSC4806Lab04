package myproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {

    @Test
    void testAddBuddyIncreasesSize() {
        AddressBook book = new AddressBook();
        assertEquals(0, book.size());
        book.addBuddy(new BuddyInfo("Ibrahim", "12345"));
        assertEquals(1, book.size());
    }

    @Test
    void testRemoveBuddyByName() {
        AddressBook book = new AddressBook();
        book.addBuddy(new BuddyInfo("saad", "67890"));
        assertTrue(book.removeBuddyByName("saad"));
        assertEquals(0, book.size());
    }
}
