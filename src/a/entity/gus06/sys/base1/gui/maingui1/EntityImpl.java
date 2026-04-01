package a.entity.gus06.sys.base1.gui.maingui1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150330";}


	private Service viewer1;
	private Service viewer2;
	private Service search1;

	private JTabbedPane tab;
	

	public EntityImpl() throws Exception
	{
		viewer1 = Outside.service(this,"*gus06.sys.base1.gui.viewer1");
		viewer2 = Outside.service(this,"*gus06.sys.base1.gui.viewer2");
		search1 = Outside.service(this,"*gus06.sys.base1.gui.search1");
		
		tab = new JTabbedPane();
		tab.addTab("ID View",(JComponent) viewer1.i());
		tab.addTab("Field View",(JComponent) viewer2.i());
		tab.addTab("Search View",(JComponent) search1.i());
	}
	
	public Object i() throws Exception
	{return tab;}
	
	
	public void p(Object obj) throws Exception
	{
		viewer1.p(obj);
		viewer2.p(obj);
		search1.p(obj);
	}
}
