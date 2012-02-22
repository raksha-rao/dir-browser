package filesys.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NodeDisplayServiceAsync {

	void displayNodes(String path, AsyncCallback<DirInfo> callback);

	//void getFileInfo(String path, AsyncCallback<FileInfo> callback);

	void getFileInfo(String path, AsyncCallback<DirInfo> callBack);

}
