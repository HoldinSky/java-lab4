package books_5;

public enum MenuOption {
	Print(1), Add(2), Remove(3), Exit(4), None(-1);

	public final int option;

	MenuOption(int option) {
		this.option = option;
	}

	public static MenuOption fromInteger(int option) {
		return switch (option) {
			case 1 -> Print;
			case 2 -> Add;
			case 3 -> Remove;
			case 4 -> Exit;
			default ->
				throw new IllegalArgumentException("Could not parse option. Min=" + Print.option + ", Max=" + Exit.option);
		};
	}
}
