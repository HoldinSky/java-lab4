package stack_24;


// Створити застосунок для вибірки елемента з стека з пріоритетами.
// Стек (5 записів) створюються в програмному коді і є об'єктом класу LinkedList.
// Запис в стек є об'єктом StackMember, що містить найменування програми (типу String),
// обсяг пам'яті для програми в мегабайтах (типу int) і пріоритет програми (типу int).
// Записи в стеці упорядковано відповідно до пріоритету і черговик додається першим в чергу свого пріоритету.

import java.util.Scanner;

public class StackApp {

	private final MyStack stack;
	private final Scanner scanner;

	private StackApp() {
		stack = new MyStack();
		scanner = new Scanner(System.in);
	}

	private void startProcess() {
		System.out.println("STARTING PROCESS");

		System.out.print("Enter process's name >> ");
		System.out.flush();
		var pName = scanner.nextLine();

		System.out.print("Enter process's capacity in Mb >> ");
		System.out.flush();
		var weight = scanner.nextInt();

		System.out.print("Enter process's priority (in 1, 2, 3, 5, 8, 13, 21) >> ");
		System.out.flush();
		var priority = scanner.nextInt();

		stack.startProcess(new StackMember(pName, weight, priority));
	}

	private void stopProcess() {
		System.out.println("STOPPING PROCESS");

		System.out.print("Enter process's priority (in 1, 2, 3, 5, 8, 13, 21) >> ");
		System.out.flush();
		var priorityToStop = scanner.nextInt();

		stack.stopProcess(priorityToStop);
	}

	private void printProcesses() {
		System.out.println("PROCESSES");

		stack.printAllProcesses();
	}

	private void menu() {
		var option = 0;
		do {
			System.out.println("MENU");
			System.out.println("1. Start process");
			System.out.println("2. Stop process");
			System.out.println("3. Print processes");
			System.out.println("4. Exit");
			System.out.print(">> ");
			System.out.flush();

			option = scanner.nextInt();
			switch (option) {
				case 1 -> startProcess();
				case 2 -> stopProcess();
				case 3 -> printProcesses();
				case 4 -> System.out.println("Exiting...");
				default -> {
				}
			}

		} while (option != 4);
	}

	public static void main(String[] args) {
		var app = new StackApp();

		app.menu();
	}
}
