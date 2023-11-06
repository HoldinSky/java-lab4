package books_5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookStoreTest {

	private final List<Book> bookList = new ArrayList<>() {{
		add(new Book("Faust", "Johann Wolfgang von Goethe", "Nash Format", 1832, 18.4));
		add(new Book("Sherlock Holmes", "Arthur Conan Doyle", "Yakaboo", 1887, 21.05));
		add(new Book("Meditations", "Marcus Aurelius", "Ancient Rome", 181, 19.52));
	}};

	@Test
	public void testAddBook() {
		var store = new BookStore(new HashMap<>());
		fillStoreWithBooks(store, bookList);

		var actual = store.getBooksByISBNs(store.getISBNs());

		assertArrayEquals(bookList.toArray(), actual.toArray());
	}

	@Test
	public void testRemove() {
		var store = new BookStore(new HashMap<>());
		fillStoreWithBooks(store, bookList);

		var isbn = store.getISBNs()[0];
		var book = store.removeBook(isbn);
		bookList.remove(book);

		var actual = store.getBooksByISBNs(store.getISBNs());

		assertArrayEquals(bookList.toArray(), actual.toArray());
	}

	private void fillStoreWithBooks(BookStore store, List<Book> books) {
		int i = (int) (Math.random() * 10_000);
		for (var book : books) {
			store.addBook(++i, book, false);
		}
	}
}
