package myproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AddressBookRepositoryTest {

    @Autowired AddressBookRepository books;

    @Test
    void saveAndLoadAddressBook() {
        AddressBook ab = new AddressBook();
        ab.addBuddy(new BuddyInfo("Alice", "123"));
        ab.addBuddy(new BuddyInfo("Bob", "456"));

        AddressBook saved = books.save(ab);
        assertThat(saved.getId()).isNotNull();

        AddressBook fromDb = books.findById(saved.getId()).orElseThrow();
        assertThat(fromDb.getBuddies()).hasSize(2);
        assertThat(fromDb.getBuddies().get(0).getName()).isEqualTo("Alice");
    }
}
