package filesys.client;

import java.io.Serializable;

public class NodeInfo implements Serializable {
	protected String path;
	public String modifiedTime;

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public NodeInfo() {
		path = null;
		modifiedTime = null;
	}

	public NodeInfo(String pathname, String time) {
		this.setPath(pathname);
		this.setModifiedTime(time);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
