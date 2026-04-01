package a.entity.gus06.appli.gusexplorer.menu.tools.icons;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20201205";}

	
	
	private Service capture;
	private Service fromClipboard;
	private Service reloadIcons;
	private Service addIconDir;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		capture = Outside.service(this,"gus06.appli.gusexplorer.action.tools.icons.capture");
		fromClipboard = Outside.service(this,"gus06.appli.gusexplorer.action.tools.icons.fromclipboard");
		reloadIcons = Outside.service(this,"gus06.appli.gusexplorer.action.tools.icons.reload");
		addIconDir = Outside.service(this,"gus06.appli.gusexplorer.action.tabs.add.icondir");
		
		menu = new JMenu("Icons");
		
		add(capture);
		add(fromClipboard);
		add(reloadIcons);
		add(addIconDir);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}