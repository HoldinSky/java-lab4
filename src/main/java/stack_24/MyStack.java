package stack_24;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class MyStack {

	private final HashMap<Integer, Stack<StackMember>> prioritizedStack;
	public static final int[] priorities = new int[] {1, 2, 3, 5, 8, 13, 21};

	public MyStack() {
		prioritizedStack = new HashMap<>();

		fillDefault();
	}

	private void fillDefault() {
		for (var priority : priorities) {
			prioritizedStack.put(priority, new Stack<>());
		}

		prioritizedStack.get(priorities[0]).push(new StackMember("Kernel", 253, priorities[0]));
		prioritizedStack.get(priorities[2]).push(new StackMember("Java_Lab4", 172, priorities[2]));
		prioritizedStack.get(priorities[5]).push(new StackMember("Firefox", 1520, priorities[5]));
		prioritizedStack.get(priorities[5]).push(new StackMember("Zoom", 362, priorities[5]));
		prioritizedStack.get(priorities[1]).push(new StackMember("Terminal", 15, priorities[1]));
	}

	public void startProcess(StackMember newProcess) {
		prioritizedStack.get(newProcess.getPriority()).add(newProcess);
	}

	public StackMember stopProcess(int priority) {
		return prioritizedStack.get(priority).pop();
	}

	public void printProcessesOfPriority(int priority) {
		prioritizedStack.get(priority).forEach(System.out::println);
	}

	public void printAllProcesses() {
		Arrays.stream(priorities).forEach(this::printProcessesOfPriority);
	}
}
