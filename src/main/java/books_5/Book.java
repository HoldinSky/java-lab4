package books_5;

public class Book implements Comparable<Book> {
	public String name;
	public String author;
	public String publishHouse;
	public int year;
	public double price;

	public Book(String name, String author, String publishHouse, int year, double price) {
		this.name = name;
		this.author = author;
		this.publishHouse = publishHouse;
		this.year = year;
		this.price = price;
	}

	@Override
	public int compareTo(Book o) {
		if (this.name.equals(o.name) && this.author.equals(o.author) && this.year == o.year)
			return 0;

		int cmp = this.name.compareTo(o.name);
		if (cmp != 0)
			return cmp;

		cmp = this.author.compareTo(o.author);
		if (cmp != 0)
			return cmp;

		cmp = this.publishHouse.compareTo(o.publishHouse);
		if (cmp != 0)
			return cmp;

		if (this.year < o.year)
			return -1;
		return 1;
	}

	@Override
	public String toString() {
		return "'" + name + "'" + " by " + author + ". Published by '" + publishHouse + "' in " + year;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublishHouse() {
		return publishHouse;
	}

	public int getYear() {
		return year;
	}

	public double getPrice() {
		return price;
	}
}
