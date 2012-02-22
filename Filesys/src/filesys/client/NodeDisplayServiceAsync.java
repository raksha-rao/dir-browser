package filesys.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface NodeDisplayServiceAsync {

	void displayNodes(String path, AsyncCallback<DirInfo> callback);

		void getNodeInfo(String path, AsyncCallback<NodeInfo> callBack);

}
