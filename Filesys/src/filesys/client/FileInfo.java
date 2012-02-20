package filesys.client;

import java.io.Serializable;

public class FileInfo extends NodeInfo implements Serializable {
	private long size;

	public FileInfo() {

		super();
		setSize(0);
	}

	public FileInfo(String path, long time, int size) {

		super(path, time);
		this.setSize(size);
	}

	public long getSize() {
		return this.size;

	}

	public void setSize(long fileSize) {
		this.size = fileSize;
	}

}
