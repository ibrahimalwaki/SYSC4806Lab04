package myproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class AddressBookViewController {

    private final AddressBookRepository books;

    public AddressBookViewController(AddressBookRepository books) {
        this.books = books;
    }

    /** Show all address books */
    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("books", books.findAll());
        return "books"; // maps to src/main/resources/templates/books.html
    }

    /** Show one address book with its buddies */
    @GetMapping("/{id}")
    public String showOne(@PathVariable Long id, Model model) {
        AddressBook book = books.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book"; // maps to src/main/resources/templates/book.html
    }
}
