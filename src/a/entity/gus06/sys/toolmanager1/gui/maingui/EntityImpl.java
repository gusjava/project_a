package a.entity.gus06.sys.toolmanager1.gui.maingui;

import a.framework.*;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250211";}


	private JTabbedPane tab;

	public EntityImpl() throws Exception
	{
		tab = new JTabbedPane();
	}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	public void p(Object obj) throws Exception
	{
		
	}
}
