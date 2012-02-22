package filesys.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DirInfo extends NodeInfo implements Serializable {
	private List<DirInfo> dirList = new ArrayList<DirInfo>();
	private List<FileInfo> fileList = new ArrayList<FileInfo>();
	int countFiles;
	
	public int getCountFiles() {
		return countFiles;
	}

	public void setCountFiles(int countFiles) {
		this.countFiles = countFiles;
	}

	public DirInfo() {
		super();
		setDirList(null);
		setFileList(null);
		countFiles=0;
	}

	public DirInfo(String path, String time, List<DirInfo> dirList,
			List<FileInfo> fileList,int count) {
		super(path, time);
		this.setDirList(dirList);
		this.setFileList(fileList);
		this.setCountFiles(count);
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
