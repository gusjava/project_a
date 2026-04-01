package a.entity.gus06.appli.gusexplorer.menu.tools.clipboard;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.Action;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20191020";}

	
	private Service clipboardGatherText;
	private Service snapshot;
	private Service search;
	
	private JMenu menu;


	public EntityImpl() throws Exception
	{
		clipboardGatherText = Outside.service(this,"gus06.appli.gusexplorer.action.tools.clipboard.gather.text");
		snapshot = Outside.service(this,"gus06.appli.gusexplorer.action.tools.clipboard.snapshot");
		search = Outside.service(this,"gus06.appli.gusexplorer.action.tools.clipboard.search");
		
		menu = new JMenu("Clipboard");
		add(clipboardGatherText);
		add(snapshot);
		add(search);
	}
	
	
	public Object i() throws Exception
	{return menu;}
	
	
	public void add(Service s) throws Exception
	{
		Action a = (Action) s.g();
		if(a!=null) menu.add(a);
	}
}