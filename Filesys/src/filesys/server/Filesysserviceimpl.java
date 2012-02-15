package filesys.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import filesys.client.DirInfo;
import filesys.client.FileInfo;
import filesys.client.FilesysService;

public class Filesysserviceimpl extends RemoteServiceServlet implements
		FilesysService

{
	DirInfo rootDir;

	public DirInfo dispfilesys(String path) {

		File root = new File(path);
		if (!root.isDirectory()) {
			throw new RuntimeException("Path [" + path
					+ "] is not a directory!");
		}

		return populateDirectoryInfo(root);
	}

	private FileInfo populateFileInfo(File file) {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setPath(file.getAbsolutePath());
		// TODO: Identify the actual file size and set it
		fileInfo.setSize(100);

		return fileInfo;
	}

	private DirInfo populateDirectoryInfo(File rootDir) {
		DirInfo dir = new DirInfo();
		dir.setPath(rootDir.getAbsolutePath());

		File[] children = rootDir.listFiles();
		
		if (children != null) {
			List<DirInfo> subDirectories = new ArrayList<DirInfo>();
			List<FileInfo> files = new ArrayList<FileInfo>();

			for (File child : children) {
				if (child.isDirectory()) {
					subDirectories.add(populateDirectoryInfo(child));
				} else {
					files.add(populateFileInfo(child));
				}
			}

			dir.setDirList(subDirectories);
			dir.setFileList(files);
		}

		return dir;
	}
}

// TreeItem subitem = new TreeItem(list[i].getName());
// subitem.addItem("list[i]");
// tree.addItem(item);
// walk(list[i]);

