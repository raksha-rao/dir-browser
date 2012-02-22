package filesys.client;

//import java.io.*;

import java.sql.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.HorizontalPanel;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SourcesClickEvents;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NodeDisplay implements EntryPoint {

	public NodeDisplayServiceAsync nodeObject = GWT.create(NodeDisplayService.class);
	public String pathName = "/home/fedora/workspace";

	private Tree tree = new Tree();
	private Label pathLabel = new Label();
	private Label fileInfoLabel=new Label();
	private VerticalPanel vPanel= new VerticalPanel();

	@SuppressWarnings("deprecation")
	HorizontalSplitPanel hPanel = new HorizontalSplitPanel();

	// @SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public void onModuleLoad() {

		displayNodeInfo();
		hPanel.setHeight("475px");
		hPanel.setSplitPosition("400px");
		hPanel.setLeftWidget(tree);
		vPanel.add(fileInfoLabel);
		vPanel.add(pathLabel);
		//vPanel.add(fileInfoLabel);
		hPanel.setRightWidget(vPanel);
	//	hPanel.setRightWidget(fileInfoLabel);

		RootPanel.get("testlist").add(hPanel);

	}

	private TreeItem createDirNode(DirInfo rootDir) {
		TreeItem node = new TreeItem(rootDir.getPath());

		for (DirInfo dir : rootDir.getDirList()) {
			node.addItem(createDirNode(dir));
		}
		for (FileInfo file : rootDir.getFileList()) {
			node.addItem(file.getPath());
		}

		return node;
	}

	void updateScreen(DirInfo result) {
		final TreeItem rootItem = createDirNode(result);
		tree.addItem(rootItem);
		
		
	}

	// }

	void displayNodeInfo() {
		if (nodeObject == null) {
			nodeObject = GWT.create(NodeDisplayService.class);
		}

		final AsyncCallback<DirInfo> callback = new AsyncCallback<DirInfo>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			public void onSuccess(DirInfo result) {
				updateScreen(result);
			}
		};

		nodeObject.displayNodes(pathName, callback);
		
	
		
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {

			@SuppressWarnings("deprecation")
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {
//NodeInfo Object=event.getClass();
			//	Date date = new Date(Long.parseLong(result.getModifiedTime()));
				// String eol = System.getProperty("line.separator");
				String path=event.getSelectedItem().getText();
				pathLabel.setText("Path:\n"
						+ path + "\n"
						//+ "TimeStamp:"
						//+ DateTimeFormat.getMediumDateFormat().format(date)
						//+result.getClass()
						//+event.getClass()
						);
				
				if (nodeObject == null) {
					nodeObject = GWT.create(NodeDisplayService.class);
				}

				final AsyncCallback<DirInfo> callBack = new AsyncCallback<DirInfo>() {
					public void onFailure(Throwable caught) {
						// TODO: Do something with errors.
					}

					public void onSuccess(DirInfo DirInfo) {
						displayFileInfo(DirInfo);
					}
				};

				
				
				nodeObject.getFileInfo(path, callBack);
			
			}
		});
		
}
	void displayFileInfo(DirInfo dirInfo)
	{
		Date date = new Date(Long.valueOf(dirInfo.getModifiedTime()));
		fileInfoLabel.setText("Time"+
				//dirInfo.getModifiedTime()
				
				 DateTimeFormat.getMediumDateFormat().format(date)+
				 "Count Of files:"+
				 dirInfo.getCountFiles()
				);
				//+"FileSize:"+ dirInfo.getSize());
		//fileInfoLabel.setText("\nDone");
	}

}
