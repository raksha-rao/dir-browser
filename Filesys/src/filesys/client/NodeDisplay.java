package filesys.client;

//import java.io.*;

import java.sql.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.client.DateTimeFormat;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.user.client.ui.RootPanel;

import com.google.gwt.user.client.ui.HorizontalSplitPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class NodeDisplay implements EntryPoint {

	public NodeDisplayServiceAsync nodeObject = GWT
			.create(NodeDisplayService.class);
	public String pathName = "/home/fedora/workspace";
	public String path;
	private Tree tree = new Tree();
	private Label pathLabel = new Label();
	private Label infoLabel = new Label();

	private Label timeLabel = new Label();
	private VerticalPanel vPanel = new VerticalPanel();

	HorizontalSplitPanel hPanel = new HorizontalSplitPanel();

	// @SuppressWarnings("deprecation")
	public void onModuleLoad() {

		displayNodeInfo();
		displaySelectedItem();
		hPanel.setHeight("475px");
		hPanel.setSplitPosition("400px");
		hPanel.setLeftWidget(tree);
		vPanel.add(pathLabel);
		vPanel.add(timeLabel);
		vPanel.add(infoLabel);

		hPanel.setRightWidget(vPanel);

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

	void displayTree(DirInfo result) {
		final TreeItem rootItem = createDirNode(result);
		tree.addItem(rootItem);

	}

	void displayNodeInfo() {
		if (nodeObject == null) {
			nodeObject = GWT.create(NodeDisplayService.class);
		}

		final AsyncCallback<DirInfo> callback = new AsyncCallback<DirInfo>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			public void onSuccess(DirInfo result) {
				displayTree(result);
			}
		};

		nodeObject.displayNodes(pathName, callback);

	}

	private void displaySelectedItem() {
		// TODO Auto-generated method stub
		tree.addSelectionHandler(new SelectionHandler<TreeItem>() {

			@SuppressWarnings("deprecation")
			@Override
			public void onSelection(SelectionEvent<TreeItem> event) {

				// String eol = System.getProperty("line.separator");
				path = event.getSelectedItem().getText();
				pathLabel.setText("Absolute Path:	" + path);

				displayItemInfo();
			}
		});
	}

	private void displayItemInfo() {

		final AsyncCallback<NodeInfo> callBack = new AsyncCallback<NodeInfo>() {
			public void onFailure(Throwable caught) {
				// TODO: Do something with errors.
			}

			public void onSuccess(NodeInfo nodeInfo) {
				if (nodeInfo instanceof DirInfo) {
					// DirInfo dirInfo =new DirInfo();
					// dirInfo= nodeInfo;
					displayDirInfo(nodeInfo);
				} else
					displayFileInfo(nodeInfo);
			}
		};

		nodeObject.getNodeInfo(path, callBack);

	}

	@SuppressWarnings("deprecation")
	void displayFileInfo(NodeInfo currentFileInfo) {
		FileInfo fileInfo = new FileInfo();
		fileInfo = (FileInfo) currentFileInfo;
		displayModifiedTime(fileInfo);
		infoLabel.setText("FileSize:	" + fileInfo.getSize());

	}

	void displayDirInfo(NodeInfo currentDirInfo) {
		DirInfo dirInfo = new DirInfo();
		dirInfo = (DirInfo) currentDirInfo;
		displayModifiedTime(dirInfo);
		infoLabel.setText("Fie Count:	" + dirInfo.getCountFiles());
	}

	private void displayModifiedTime(NodeInfo dirInfo) {
		Date date = new Date(Long.valueOf(dirInfo.getModifiedTime()));
		timeLabel.setText("Last Modified Time	" +

		DateTimeFormat.getMediumDateFormat().format(date));
	}
}
