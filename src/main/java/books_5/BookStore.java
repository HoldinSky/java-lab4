package books_5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class BookStore {
	private final HashMap<Integer, Book> catalogue;

	public BookStore() {
		this.catalogue = new HashMap<>();
		fillDefaultCatalogue();
	}

	public BookStore(HashMap<Integer, Book> catalogue) {
		this.catalogue = catalogue;
	}

	private void fillDefaultCatalogue() {
		catalogue.put(1, new Book("Faust", "Johann Wolfgang von Goethe", "Nash Format", 1832, 18.4));
		catalogue.put(2, new Book("Kobzar", "Taras Shevchenko", "Nash Format", 1840, 14.39));
		catalogue.put(3, new Book("Sherlock Holmes", "Arthur Conan Doyle", "Yakaboo", 1887, 21.05));
		catalogue.put(4, new Book("The Sea Wolf", "Jack London", "SeaWolfPublish", 1904, 11.99));
		catalogue.put(5, new Book("A Song of Ice and Fire", "George R.R. Martin", "KM-Books", 2011, 106.1));
	}

	public List<Book> getBooksByISBNs(int... codes) {
		List<Book> books = new ArrayList<>();

		for (int isbn : codes) {
			try {
				var book = catalogue.get(isbn);
				books.add(book);
			}
			catch (Throwable e) {
				System.err.println("ERROR: " + e.getMessage());
			}
		}

		return books;
	}

	public void printBooks(List<Book> books) {
		books.forEach(System.out::println);
	}

	public void addBook(int isbn, Book book, boolean replaceIfExists) {
		if (replaceIfExists)
			catalogue.put(isbn, book);
		else
			catalogue.putIfAbsent(isbn, book);
	}

	public Book removeBook(int isbn) {
		var book = catalogue.get(isbn);
		catalogue.remove(isbn);

		return book;
	}

	public List<Book> getSortedBooks(int limit) {
		var isbnCodes = catalogue.keySet().stream().sorted().mapToInt(Integer::intValue).limit(limit).toArray();
		return getBooksByISBNs(isbnCodes);
	}

	public int[] getISBNs() {
		return catalogue.keySet().stream().mapToInt(Integer::intValue).toArray();
	}

	public void sortBookList(List<Book> bookList) {
		sortBookList(bookList, SortOption.Name);
	}

	public void sortBookList(List<Book> bookList, SortOption option) {
		switch (option.order) {
			case 0 -> bookList.sort(Comparator.naturalOrder());
			case 1 -> bookList.sort(Comparator.comparing(book -> book.author));
			case 2 -> bookList.sort(Comparator.comparing(book -> book.publishHouse));
			case 3 -> bookList.sort(Comparator.comparing(book -> book.year));
			case 4 -> bookList.sort(Comparator.comparing(book -> book.price));
		}
	}
}
