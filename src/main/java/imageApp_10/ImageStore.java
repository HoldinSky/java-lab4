package imageApp_10;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class ImageStore {

	private static final String ProjectPath = "/home/nazar/prg/java/lab4/";

	private final HashMap<String, Image> images;
	private final TreeSet<String> uniqueImages;

	public ImageStore() {
		this.images = new HashMap<>();
		this.uniqueImages = new TreeSet<>();

		fillDefault();
	}


	private void fillDefault() {
		images.put("elixir.png", new Image(ProjectPath, "data/elixir.png", 860, 1269));
		images.put("java.jpeg", new Image(ProjectPath, "data/java.jpeg", 1280, 768));
		images.put("kotlin.png", new Image(ProjectPath, "data/kotlin.png", 2400, 2400));
		images.put("lisp.png", new Image(ProjectPath, "data/lisp.png", 1935, 1161));
		images.put("rust.png", new Image(ProjectPath, "data/rust.png", 460, 307));

		uniqueImages.addAll(images.keySet());
	}

	public boolean addImage(String name, String pathToImage, int[] properties) {
		if (!uniqueImages.add(name))
			return false;

		images.put(name, new Image(pathToImage, name, properties[0], properties[1]));

		return true;
	}

	public void removeImage(String name) {
		if (!uniqueImages.contains(name))
			return;

		images.remove(name);
		uniqueImages.remove(name);

	}

	public List<Image> getImages(String... names) {
		var enquiredImages = new ArrayList<Image>();

		for (var name : names) {
			if (!uniqueImages.contains(name))
				continue;

			enquiredImages.add(images.get(name));
		}

		return enquiredImages;
	}

	public List<Image> getImages(List<String> names) {
		var arr = new String[names.size()];
		return getImages(names.toArray(arr));
	}

	public List<Image> getImages(int limit) {
		var enquiredImages = uniqueImages.stream().limit(limit).toList();

		return enquiredImages.stream().map(images::get).toList();
	}

	public void printImages() {
		String[] arr = new String[uniqueImages.size()];
		printImages(uniqueImages.toArray(arr));
	}

	public void printImages(String ... names) {
		for (var name : names) {
			System.out.println(images.get(name));
		}
	}

	public void printImages(List<Image> imagesToPrint) {
		String[] arr = new String[uniqueImages.size()];
		printImages(imagesToPrint.stream().map(Image::getPathToImage).toList().toArray(arr));
	}
}
