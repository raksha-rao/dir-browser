package filesys.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DirInfo extends NodeInfo implements Serializable {
	private List<DirInfo> dirList = new ArrayList<DirInfo>();
	private List<FileInfo> fileList = new ArrayList<FileInfo>();

	public DirInfo() {
		super();
		setDirList(null);
		setFileList(null);
	}

	public DirInfo(String path, long time, List<DirInfo> dirList,
			List<FileInfo> fileList) {
		super(path, time);
		this.setDirList(dirList);
		this.setFileList(fileList);
	}

	public void setDirList(List<DirInfo> dirList) {
		this.dirList = dirList;
	}

	public List<DirInfo> getDirList() {
		return dirList;
	}

	public void setFileList(List<FileInfo> fileList) {
		this.fileList = fileList;
	}

	public List<FileInfo> getFileList() {
		return fileList;
	}

	
}
