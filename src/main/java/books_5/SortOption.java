package books_5;

public enum SortOption {
	Name(0), Author(1), PublishHouse(2), Year(3), Price(4);

	public final int order;

	SortOption(int order) {
		this.order = order;
	}

	public static SortOption fromInteger(int option) {
		return switch (option) {
			case 0 -> Name;
			case 1 -> Author;
			case 2 -> PublishHouse;
			case 3 -> Year;
			case 4 -> Price;
			default ->
				throw new IllegalArgumentException("Could not parse option. Min=" + Name.order + ", Max=" + Price.order);
		};
	}
}
