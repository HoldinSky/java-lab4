package stack_24;

public class StackMember {

	private final String processName;
	private int memoryTakenMb;
	private int priority;

	public StackMember(String processName, int memoryTakenMb, int priority) {
		this.processName = processName;
		this.memoryTakenMb = memoryTakenMb;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "StackMember {" + processName + "; RAM: " + memoryTakenMb + " Mb; priority: " + priority;
	}

	public String getProcessName() {
		return processName;
	}

	public int getMemoryTakenMb() {
		return memoryTakenMb;
	}

	public int getPriority() {
		return priority;
	}
}
