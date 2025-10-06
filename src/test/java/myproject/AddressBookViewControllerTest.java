package myproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressBookViewControllerTest {

    @Autowired MockMvc mvc;
    @Autowired AddressBookRepository books;

    @Test
    void bookPageShowsBuddyName() throws Exception {
        // Arrange: create a book with one buddy in the test DB (H2 in-memory)
        AddressBook ab = new AddressBook();
        ab.addBuddy(new BuddyInfo("TestBuddy", "111-222"));
        ab = books.save(ab);

        // Act + Assert: GET /books/{id} renders HTML containing the buddy
        mvc.perform(get("/books/" + ab.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Address Book")))
                .andExpect(content().string(containsString("TestBuddy")))
                .andExpect(content().string(containsString("111-222")));
    }
}
