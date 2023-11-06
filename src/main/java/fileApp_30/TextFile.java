package fileApp_30;

public class TextFile {

	private final String pathToFile;
	private final String description;

	public TextFile(String pathToFile, String description) {
		this.pathToFile = pathToFile;
		this.description = description;
	}

	@Override
	public String toString() {
		return "File {" + pathToFile + "; " + description + "}";
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public String getDescription() {
		return description;
	}
}
