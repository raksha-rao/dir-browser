package filesys.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FilesysServiceAsync {

	void dispfilesys(String path, AsyncCallback<DirInfo> callback);

}
