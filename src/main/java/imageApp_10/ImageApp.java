package imageApp_10;


// Створити застосунок для пошуку зображення в списку зображень.
// Початкові записи в списку (для 5 зображень) створюються в програмному коді і є об'єктом класу HashMap,
// де ключем є найменування зображення (типу String), а значенням - зображення (об'єкт класу Image).
// За допомогою TreeSet забезпечити, щоб зображення додавалися з унікальними іменами.

import books_5.MenuOption;

import java.util.Arrays;
import java.util.Scanner;

public class ImageApp {

	private MenuOption menuOption = MenuOption.None;
	private final Scanner scanner = new Scanner(System.in);

	private final ImageStore store;

	private ImageApp() {
		store = new ImageStore();
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
		System.out.println("1. Print images info");
		System.out.println("2. Add image");
		System.out.println("3. Remove image");
		System.out.println("4. Exit");
		System.out.print(">> ");
		System.out.flush();
	}

	private void processMenuOption() {
		switch (menuOption) {
			case Print -> processPrintImages();
			case Add -> processAddImage();
			case Remove -> processRemoveImage();
			case None -> System.err.println("Bad menu option has been set " + menuOption);
			case Exit -> {
			}
		}
	}

	private void processPrintImages() {
		printImagesMenu();

		var opt = scanner.nextInt();
		scanner.nextLine();
		switch (opt) {
			case 1 -> {
				System.out.print("How many of images you want to see? >> ");
				System.out.flush();

				var limit = scanner.nextInt();
				var images = store.getImages(limit);

				clearScreen();
				store.printImages(images);
			}
			case 2 -> {
				System.out.print("Input image names split by space. >> ");
				System.out.flush();

				var isbnStr = scanner.nextLine();
				var images = store.getImages(Arrays.stream(isbnStr.split(" ")).toList());

				clearScreen();
				store.printImages(images);
			}
			default -> {
			}
		}
	}

	private void printImagesMenu() {
		System.out.println("PRINTING IMAGES");
		System.out.println("Choose print option");
		System.out.println("1. Sorted");
		System.out.println("2. By image names");
		System.out.print(">> ");
		System.out.flush();
	}

	private void processAddImage() {
		System.out.println("ADDING NEW IMAGE");
		System.out.print("Enter path to file >> ");
		System.out.flush();
		var imagePath = scanner.next();
		scanner.skip("\n");

		System.out.print("Enter image's width (int) >> ");
		System.out.flush();
		var width = scanner.nextInt();
		scanner.skip("\n");

		System.out.print("Enter image's height (int) >> ");
		System.out.flush();
		var height = scanner.nextInt();
		scanner.skip("\n");

		var name = imagePath.substring(imagePath.lastIndexOf("/") + 1);
		store.addImage(name, imagePath.substring(0, imagePath.lastIndexOf("/")), new int[]{width, height});
	}

	private void processRemoveImage() {
		System.out.println("REMOVING IMAGE");
		System.out.print("Enter image's name >> ");
		System.out.flush();

		var name = scanner.next();

		store.removeImage(name);
	}

	public static void main(String[] args) {
		var app = new ImageApp();

		app.menu();
	}
}
