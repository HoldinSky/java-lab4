package imageApp_10;

public class Image {

	private final String pathToImage;
	private final String imageName;
	private final int width;
	private final int height;

	public Image(String pathToImage, String imageName, int width, int height) {
		this.pathToImage = pathToImage;
		this.imageName = imageName;
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return "Image {'" + pathToImage + "/" + imageName + "'; w: " + width + "; h: " + height + "}";
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getImageName() {return imageName;}
}
