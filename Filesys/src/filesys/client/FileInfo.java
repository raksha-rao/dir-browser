package filesys.client;

import java.io.Serializable;

public class FileInfo implements Serializable {
	String path;
	private int size;

	public FileInfo() {
		path = null;
		setSize(0);
	}

	public FileInfo(String path, int size) {
		this.path = path;
		this.setSize(size);
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return this.size;

	}

	public void setSize(int size) {
		this.size = size;
	}

}
