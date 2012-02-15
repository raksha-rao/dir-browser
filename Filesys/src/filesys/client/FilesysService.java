package filesys.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("dir")
public interface FilesysService extends RemoteService{
	DirInfo dispfilesys(String path);
}
