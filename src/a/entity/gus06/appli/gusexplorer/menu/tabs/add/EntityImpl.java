package a.entity.gus06.appli.gusexplorer.menu.tabs.add;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Action;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20151006";}

	
	private Service addFromClipboard;
	private Service addFromClipboardAsPath;
	private Service addTxtFile;
	private Service addGusFile;
	
	private Service addTempDir;
	private Service addIconDir;
	private Service addAppRootDir;
	private Service addEditorConfigDir;
	private Service addScriptDir_h1;
	private Service addScriptDir_h2;
	private Service addScriptDir_h3;
	private Service addScriptDir_h4;
	private Service addScriptDir_tools;
	private Service addScriptDir_bottom;
	private Service addScriptDir_startup;
	private Service addScriptDir_scheduling;
	
	private Service addRoots;
	private Service addDesktop;
	private Service addStartup;
	
	
	private JMenu menu;

	public EntityImpl() throws Exception
	{
		addFromClipboard = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.fromclipboard");
		addFromClipboardAsPath = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.fromclipboard.aspath");
		addTxtFile = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.temp.emptytxtfile");
		addGusFile = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.temp.emptygusfile");
		
		addTempDir = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.temp.dir");
		addIconDir = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.icondir");
		addAppRootDir = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.approotdir");
		addEditorConfigDir = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.editorconfigdir");
		addScriptDir_h1 = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.h.txt");
		addScriptDir_h2 = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.h.db");
		addScriptDir_h3 = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.h.dir");
		addScriptDir_h4 = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.h.file");
		addScriptDir_tools = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.tools");
		addScriptDir_bottom = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.bottom");
		addScriptDir_startup = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.startup");
		addScriptDir_scheduling = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.scriptdir.scheduling");
		
		addRoots = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.roots");
		addDesktop = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.desktop");
		addStartup = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.startup");
	
		menu = new JMenu("Add new tabs");
		
		add(addFromClipboard);
		add(addFromClipboardAsPath);
		add(addTxtFile);
		add(addGusFile);
		
		menu.addSeparator();
		
		add(addAppRootDir);
		add(addTempDir);
		add(addIconDir);
		add(addEditorConfigDir);
		
		menu.addSeparator();
		
		add(addScriptDir_h1);
		add(addScriptDir_h2);
		add(addScriptDir_h3);
		add(addScriptDir_h4);
		
		menu.addSeparator();
		
		add(addScriptDir_tools);
		add(addScriptDir_bottom);
		add(addScriptDir_startup);
		add(addScriptDir_scheduling);
		
		menu.addSeparator();
		
		add(addRoots);
		add(addDesktop);
		add(addStartup);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}