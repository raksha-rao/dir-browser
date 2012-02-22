package filesys.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import filesys.client.NodeDisplayService;
import filesys.client.NodeInfo;
import filesys.client.DirInfo;
import filesys.client.FileInfo;

public class NodeDisplayServiceImpl extends RemoteServiceServlet implements
		NodeDisplayService

{

	public DirInfo displayNodes(String path) {

		File root = new File(path);
		if (!root.isDirectory()) {
			throw new RuntimeException("Path [" + path
					+ "] is not a directory!");

		}
		return populateDirectoryInfo(root);

		/*
		 * else { System.out.print("Server Side"); return null; }
		 */
	}

	private FileInfo populateFileInfo(File file) {
		FileInfo fileInfo = new FileInfo();
		populateNodeInfo(file, fileInfo);
		fileInfo.setSize(file.length());
		return fileInfo;
	}

	private void populateNodeInfo(File source, NodeInfo target) {
		target.setPath(source.getAbsolutePath());
		target.setModifiedTime(String.valueOf(source.lastModified()));
	}

	private DirInfo populateDirectoryInfo(File rootDir) {
		DirInfo dir = new DirInfo();

		populateNodeInfo(rootDir, dir);
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

	public NodeInfo getNodeInfo(String path) {

		File file = new File(path);

		if (file.isDirectory()) {
			DirInfo dir = new DirInfo();
			dir.setPath(path);
			dir.setModifiedTime(String.valueOf(file.lastModified()));

			dir.setCountFiles(countFiles(file));
			return dir;
		}

		else {
			FileInfo fileInfo = new FileInfo();
			fileInfo.setPath(path);
			fileInfo.setModifiedTime(String.valueOf(file.lastModified()));
			fileInfo.setSize(file.length());
			return fileInfo;
		}
	}

	int countFiles(File file) {
		int count = 0;
		File[] children = file.listFiles();

		if (children != null) {

			for (File child : children) {
				if (child.isFile())
					count++;

			}
		}
		return count;
	}
}
