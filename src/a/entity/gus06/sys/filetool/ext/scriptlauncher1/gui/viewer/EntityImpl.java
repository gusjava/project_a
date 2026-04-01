package a.entity.gus06.sys.filetool.ext.scriptlauncher1.gui.viewer;

import a.framework.*;
import javax.swing.JTabbedPane;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P, I, V {

	public String creationDate() {return "20161111";}


	private Service fileViewer;
	private Service dirViewer;
	private Service testViewer;
	private Service depViewer;
	private Service mappingViewer;
	private Service searchViewer;

	private JTabbedPane tab;
	

	public EntityImpl() throws Exception
	{
		fileViewer = Outside.service(this,"*gus06.file.editor.ext.gus");
		dirViewer = Outside.service(this,"*gus06.dir.explorer.simple");
		testViewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.test");
		depViewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.dependencies");
		mappingViewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.mapping");
		searchViewer = Outside.service(this,"*gus06.sys.filetool.ext.scriptlauncher1.gui.viewer.search");
		
		tab = new JTabbedPane();
		tab.addTab("Script",(JComponent) fileViewer.i());
		tab.addTab("Directory",(JComponent) dirViewer.i());
		tab.addTab("Test",(JComponent) testViewer.i());
		tab.addTab("Dependencies",(JComponent) depViewer.i());
		tab.addTab("Mapping",(JComponent) mappingViewer.i());
		tab.addTab("Search",(JComponent) searchViewer.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{
			fileViewer.p(null);
			dirViewer.p(null);
			testViewer.p(null);
			depViewer.p(null);
			mappingViewer.p(null);
			searchViewer.p(null);
			return;
		}
		
		File file = (File) obj;
		File dir = file.getParentFile();
		
		fileViewer.p(file);
		dirViewer.p(dir);
		testViewer.p(file);
		depViewer.p(file);
		mappingViewer.p(file);
		searchViewer.p(file);
	}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("mappingFile"))
		{
			mappingViewer.v("mappingFile",obj);
			return;
		}
		if(key.equals("root"))
		{
			depViewer.v("root",obj);
			searchViewer.v("root",obj);
			return;
		}
		if(key.equals("scriptName"))
		{
			depViewer.v("scriptName",obj);
			searchViewer.v("scriptName",obj);
			return;
		}
		if(key.equals("scriptPath"))
		{
			depViewer.v("scriptPath",obj);
			return;
		}
		throw new Exception("Unknown key: "+key);
	}
}