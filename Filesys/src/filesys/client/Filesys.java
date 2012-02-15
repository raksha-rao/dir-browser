package filesys.client;


//import java.io.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;


import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.RootPanel;
//import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

public class Filesys implements EntryPoint {

	 public FilesysServiceAsync filed = GWT.create(FilesysService.class);
	  public String path="/home/fedora/Filesys";
 
//private TextBox newtext = new TextBox();
 // private HorizontalPanel addPanel = new HorizontalPanel();
  private Tree tree= new Tree();
  
 	  
  
  /**
   * Entry point method.
   */
  //@SuppressWarnings("deprecation")
public void onModuleLoad() {
	//  String path="/home/fedora/test";
	  
	 
	/*  TreeItem item=new TreeItem("root"); 
	  TreeItem test=new TreeItem("test");
	  
	  item.addItem("root1");
	  item.addItem("root2");
	  
	  test.addItem("test1");
	  test.addItem("test2");
	  
	  tree.addItem(item);
	  tree.addItem(test);
	 */
		
	  
	 // addPanel.add(newtext);
	 
	  display();
	  RootPanel.get("testlist").add(tree);
  }

private TreeItem createDirNode(DirInfo rootDir) {
	TreeItem node = new TreeItem(rootDir.getPath());
	for(DirInfo dir : rootDir.getDirList()) {
		node.addItem(createDirNode(dir));
	}
	for(FileInfo file : rootDir.getFileList()) {
		node.addItem(file.getPath());
	}
	return node;
}

void updatescreen(DirInfo result)
{
	TreeItem rootItem = createDirNode(result);
	tree.addItem(rootItem);
}
//}

void display()
 {
	 if (filed == null) {
	      filed = GWT.create(FilesysService.class);
	    }

	   AsyncCallback<DirInfo> callback = new AsyncCallback<DirInfo>() {
	      public void onFailure(Throwable caught) {
	        // TODO: Do something with errors.
	      }

	      public void onSuccess(DirInfo result) {
	        updatescreen(result);
	      }
	    };
	    
	    
	 filed.dispfilesys(path,callback);
 }
}
//
	  /*File root=new File();
	
	 
	 
      walk(root);
	
	  RootPanel.get("testlist").add(addPanel);
	  
		
  }
   

	public void walk(File root){
		 //File root=new File(path);
		 TreeItem item=new TreeItem("root.getName()");  
	//	  File[] list=root.listFiles();
		 
		  
		  for(int i=0;list[i]!= null;i++){
			  item.addItem(list[i].getName());
		  }
		  for(int i = 0;list[i]!=null;i++){
			  if(list[i].isDirectory()){
				//  TreeItem subitem = new TreeItem(list[i].getName()); 
				 // subitem.addItem("list[i]");
				 // tree.addItem(item);
				  walk(list[i]);
				  
			  
			  }
		  }
	}*/
