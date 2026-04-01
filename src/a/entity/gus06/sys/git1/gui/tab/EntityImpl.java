package a.entity.gus06.sys.git1.gui.tab;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201128";}
	
	
	private Service tab;
	private Service panel1;
	private Service panel2;

	public EntityImpl() throws Exception
	{
		tab = Outside.service(this,"*gus06.swing.tabbedpane.holder1");
		panel1 = Outside.service(this,"*gus06.sys.git1.gui.panel1");
		panel2 = Outside.service(this,"*gus06.sys.git1.gui.panel2");
		
		tab.v("GIT_branchs#Branchs",panel1.i());
		tab.v("GIT_authors#Authors",panel2.i());
	}
	
	
	public Object i() throws Exception
	{return tab.i();}
	
	
	public void p(Object obj) throws Exception
	{
		panel1.p(obj);
		panel2.p(obj);
	}
}