package a.entity.gus06.sys.base2.gui.maingui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20221104";}


	private Service viewGui;
	private Service backupGui;

	private Object base;
	private JTabbedPane tab;
	

	public EntityImpl() throws Exception
	{
		viewGui = Outside.service(this,"*gus06.sys.base2.gui.panel1");
		backupGui = Outside.service(this,"*gus06.sys.base2.gui.backup");
		
		tab = new JTabbedPane();
		tab.addTab("View",(JComponent) viewGui.i());
		tab.addTab("Backup",(JComponent) backupGui.i());
	}
	
	
	public Object i() throws Exception
	{return tab;}
	
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		
		viewGui.p(base);
		backupGui.p(base);
	}
}