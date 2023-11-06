package books_5;

// Створити застосунок для перегляду списку книг і видалення книг в бібліотечному каталозі.
// Початкові записи в списку (5 записів) є об'єктами класу HashMap, де ключем є індекс ISBN книги (типу Integer),
// а значенням - об'єкт Book, що містить найменування книги, прізвище та ім'я учасника, видавництво (всі поля типу String),
// рік видання (типу int) і ціну книги (типу double). Передбачити можливість сортування книг по 2-3 полях, для чого використовувати ArrayList.

import java.util.Arrays;
import java.util.Scanner;



public class BookApp {

	private MenuOption menuOption = MenuOption.None;
	private final Scanner scanner = new Scanner(System.in);

	private final BookStore store;

	private BookApp() {
		store = new BookStore();
	}

	private void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	private void menu() {
		do {
			clearScreen();
			printMenu();

			try {
				var option = scanner.nextInt();
				menuOption = MenuOption.fromInteger(option);
				scanner.nextLine();

				clearScreen();
				processMenuOption();

				if (menuOption != MenuOption.Exit) {
					System.out.println("Press any key and Enter to continue...");
					scanner.next();
				}
			}
			catch (Throwable e) {
				System.err.println(e.getMessage());
			}

		} while (menuOption != MenuOption.Exit);
	}

	private void printMenu() {
		System.out.println("==== MENU ====");
		System.out.println("1. Print books");
		System.out.println("2. Add book");
		System.out.println("3. Remove book");
		System.out.println("4. Exit");
		System.out.print(">> ");
		System.out.flush();
	}

	private void processMenuOption() {
		switch (menuOption) {
			case Print -> processPrintBooks();
			case Add -> processAddBook();
			case Remove -> processRemoveBook();
			case None -> System.err.println("Bad menu option has been set " + menuOption);
			case Exit -> {
			}
		}
	}

	private void processPrintBooks() {
		printBooksMenu();

		var opt = scanner.nextInt();
		scanner.nextLine();
		switch (opt) {
			case 1 -> {
				System.out.print("How many of books you want to see? >> ");
				System.out.flush();

				var limit = scanner.nextInt();
				var books = store.getSortedBooks(limit);

				clearScreen();
				store.printBooks(books);
			}
			case 2 -> {
				System.out.print("Input ISBNs split by space. >> ");
				System.out.flush();

				var isbnStr = scanner.nextLine();
				var books = store.getBooksByISBNs(Arrays.stream(isbnStr.split(" ")).mapToInt(Integer::parseInt).toArray());

				clearScreen();
				store.printBooks(books);
			}
			case 3 -> {
				System.out.print("Input ISBNs split by space. >> ");
				System.out.flush();

				var isbnStr = scanner.nextLine();
				var books = store.getBooksByISBNs(Arrays.stream(isbnStr.split(" ")).mapToInt(Integer::parseInt).toArray());

				System.out.print("Input sort option (From " + SortOption.Name + " to " + SortOption.Price + ". >> ");
				System.out.flush();

				var sortOption = SortOption.fromInteger(scanner.nextInt());
				store.sortBookList(books, sortOption);
			}
			default -> {
			}
		}
	}

	private void printBooksMenu() {
		System.out.println("PRINTING BOOK");
		System.out.println("Choose print option");
		System.out.println("1. Sorted");
		System.out.println("2. By ISBN");
		System.out.println("3. By ISBN and sorted");
		System.out.print(">> ");
		System.out.flush();
	}

	private void processAddBook() {
		System.out.println("ADDING NEW BOOK");
		System.out.print("Enter book's ISBN (integer) >> ");
		System.out.flush();
		var isbn = scanner.nextInt();
		scanner.skip("\n");

		System.out.print("Enter book's name >> ");
		System.out.flush();
		var name = scanner.nextLine();

		System.out.print("Enter book's author >> ");
		System.out.flush();
		var author = scanner.nextLine();

		System.out.print("Enter book's publish house >> ");
		System.out.flush();
		var pubHouse = scanner.nextLine();

		System.out.print("Enter book's year (int) >> ");
		System.out.flush();
		var year = scanner.nextInt();
		scanner.skip("\n");

		System.out.print("Enter book's price (double) >> ");
		System.out.flush();
		var price = scanner.nextDouble();
		scanner.skip("\n");

		System.out.print("Replace if exists? (y/n) >> ");
		System.out.flush();
		var replace = Character.toLowerCase(scanner.nextLine().charAt(0)) == 'y';

		store.addBook(isbn, new Book(name, author, pubHouse, year, price), replace);
	}

	private void processRemoveBook() {
		System.out.println("REMOVING BOOK");
		System.out.print("Enter book's ISBN (integer) >> ");
		System.out.flush();

		var isbn = scanner.nextInt();

		store.removeBook(isbn);
	}

	public static void main(String[] args) {
		var app = new BookApp();

		app.menu();
	}
}