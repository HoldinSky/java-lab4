package fileApp_30;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class FileSystem {

	private final HashMap<String, TextFile> fileSystem;

	public FileSystem() {
		this.fileSystem = new HashMap<>();

		fileSystem.put("Hello-world.txt", new TextFile("/home/nazar/prg/java", "Example of elementary program written in java"));
		fileSystem.put("Markup.txt", new TextFile("/home/nazar/prg/html", "Simple html page"));
		fileSystem.put("Styles.txt", new TextFile("/home/nazar/prg/css", "CSS for html page"));
		fileSystem.put("Faust.txt", new TextFile("/home/nazar/literature", "Immortal classic"));
		fileSystem.put("Kobzar.txt", new TextFile("/home/nazar/literature", "Ukrainian immortal classic"));
	}

	public TextFile findFile(String name) throws NoSuchElementException {
		var file = fileSystem.get(name);
		if (file == null) throw new NoSuchElementException("There is no entry with filename " + name + " in filesystem");
		return file;
	}

	public void addFile(String name, String path, String description) {
		fileSystem.putIfAbsent(name, new TextFile(path, description));
	}

	public void removeFile(String name) {
		fileSystem.remove(name);
	}

}
