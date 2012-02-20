package filesys.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DirInfo extends CommonInfo implements Serializable {
	//private String path;
	private List<DirInfo> dirList=new ArrayList<DirInfo>();
	private List<FileInfo> fileList=new ArrayList<FileInfo>();
	
	public DirInfo()
	{
		//setPath(null);
		super();
		setDirList(null);
		setFileList(null);
	}
	
	public DirInfo(String path,List<DirInfo> dirList, List<FileInfo> fileList)
	{
		//this.setPath(path);
		super(path);
		this.setDirList(dirList);
		this.setFileList(fileList);
	}
	
	/*String getPath()
	{
		return this.path;
		
	}*/

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

	public void setPath(String path) {
		this.path = path;
	}
}
