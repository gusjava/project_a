package a.entity.gus06.appli.gusexplorer.execute.tabs.selected.refactorpath;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20230119";}
	
	public static final String MESSAGE = "Please, enter new file path";


	private Service dataManager;
	private Service labelCustManager;
	private Service selection;
	private Service pathFromClipboard;
	private Service inputDialog;


	public EntityImpl() throws Exception
	{
		dataManager = Outside.service(this,"gus06.appli.gusexplorer.data.manager");
		labelCustManager = Outside.service(this,"gus06.appli.gusexplorer.labelcust.manager");
		selection = Outside.service(this,"gus06.appli.gusexplorer.gui.tabbedpane.selection");
		pathFromClipboard = Outside.service(this,"gus06.sys.clipboard1.g.string.filepath");
		inputDialog = Outside.service(this,"gus06.input.text.dialog");
	}
	
	
	public void e() throws Exception
	{
		File file1 = (File) selection.g();
		if(file1==null) return;
		
		String path2 = (String) pathFromClipboard.g();
		if(path2==null || !isFilePath(path2)) path2 = file1.getAbsolutePath();
		
		path2 = (String) inputDialog.t(new String[]{MESSAGE, path2});
		if(path2==null) return;
		
		File file2 = new File(path2);
		
		dataManager.v("modify", new File[]{file1, file2});
		labelCustManager.v("refactorPath", new File[]{file1, file2});
	}
	
	private boolean isFilePath(String path)
	{return new File(path).exists();}
}
