package myproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final AddressBookRepository books;

    public DataLoader(AddressBookRepository books) {
        this.books = books;
    }

    @Override
    public void run(String... args) {
        // Only seed if DB is empty
        if (books.count() == 0) {
            AddressBook book = new AddressBook();
            book.addBuddy(new BuddyInfo("Ibrahim", "613-700-4250"));
            book.addBuddy(new BuddyInfo("Saad", "613-413-1061"));
            books.save(book);
        }
    }
}
