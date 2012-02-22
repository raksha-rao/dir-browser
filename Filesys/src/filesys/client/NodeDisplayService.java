package filesys.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dir")
public interface NodeDisplayService extends RemoteService {
	DirInfo displayNodes(String path);

	NodeInfo getNodeInfo(String path);
}
