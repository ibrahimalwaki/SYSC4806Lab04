package myproject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuddyInfoTest {

    @Test
    void constructorAndGettersWork() {
        BuddyInfo b = new BuddyInfo("Ibrahim", "123");
        assertEquals("Ibrahim", b.getName());
        assertEquals("123", b.getPhoneNumber());
    }

    @Test
    void settersWork() {
        BuddyInfo b = new BuddyInfo("Ibrahim", "456");
        b.setName("Ibrahim");
        b.setPhoneNumber("456");
        assertEquals("Ibrahim", b.getName());
        assertEquals("456", b.getPhoneNumber());
    }

    @Test
    void toStringHasNameAndPhone() {
        BuddyInfo b = new BuddyInfo("jad", "000");
        assertTrue(b.toString().contains("jad"));
        assertTrue(b.toString().contains("000"));
    }
}
