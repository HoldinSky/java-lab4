package fileApp_30;


// Створити застосунок для пошуку в списку файлів і видалення файлу зі списку.
// Початкові записи в списку (для 5 текстових файлів з розширенням .txt) створюються в програмному коді
// і є об'єктом класу HashMap, де ключем є ім'я файлу (типу String), а значенням - об'єкт типу TextFile,
// що містить два елементи String - абсолютний шлях до файлу (без імені файлу ) і короткий опис вмісту файлу.

import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileApp {

	private final FileSystem fs;
	private final Scanner scanner;

	private FileApp() {
		fs = new FileSystem();
		scanner = new Scanner(System.in);
	}

	private void start() {
		var option = 0;

		do {
			System.out.println("MENU");
			System.out.println("1. Print file info");
			System.out.println("2. Add file");
			System.out.println("3. Remove file");
			System.out.println("4. Exit");
			System.out.print(">> ");
			System.out.flush();

			option = scanner.nextInt();

			switch (option) {
				case 1 -> getAndPrintFileInfo();
				case 2 -> addFile();
				case 3 -> removeFile();
				case 4 -> System.out.println("Finishing the program...");
				default -> {
				}
			}

		} while (option != 4);
	}

	private void getAndPrintFileInfo() {
		System.out.print("Enter file's name with extension >> ");
		System.out.flush();

		var name = scanner.nextLine();

		try {
			var file = fs.findFile(name);
			System.out.println("Filename: " + name + "; " + file);
		}
		catch (NoSuchElementException exc) {
			System.err.println(exc.getLocalizedMessage());
		}
	}

	private void addFile() {
		System.out.print("Enter file's name with extension >> ");
		System.out.flush();
		var filename = scanner.nextLine();

		System.out.print("Enter file's path >> ");
		System.out.flush();
		var path = scanner.nextLine();

		System.out.print("Enter short description (up to 50 symbols) >> ");
		System.out.flush();
		var description = scanner.nextLine();

		fs.addFile(filename, path, description);
	}

	private void removeFile() {
		System.out.print("Enter file's name with extension >> ");
		System.out.flush();
		var filename = scanner.nextLine();

		fs.removeFile(filename);
	}

	public static void main(String[] args) {
		var app = new FileApp();

		app.start();
	}
}
