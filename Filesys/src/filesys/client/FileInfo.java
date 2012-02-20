package filesys.client;

import java.io.Serializable;

public class FileInfo extends CommonInfo implements Serializable {
	//String path;
	private long size;

	public FileInfo() {
		//path = null;
		super();
		setSize(0);
	}

	public FileInfo(String path, int size) {
		//this.path = path;
		super(path);
		this.setSize(size);
	}

	/*public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}*/

	public long getSize() {
		return this.size;

	}

	public void setSize(long fileSize) {
		this.size = fileSize;
	}

}
