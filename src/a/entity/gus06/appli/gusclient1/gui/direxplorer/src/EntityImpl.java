package a.entity.gus06.appli.gusclient1.gui.direxplorer.src;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140902";}


	private Service explorer_entitySrc;
	private Service explorer_gyemSrc;
	
	private JTabbedPane tab;
	
	
	public EntityImpl() throws Exception
	{
		explorer_entitySrc = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.src.entity");
		explorer_gyemSrc = Outside.service(this,"*gus06.appli.gusclient1.gui.direxplorer.src.gyem");
		
		tab = new JTabbedPane();
		
		tab.addTab("Entity src",(JComponent) explorer_entitySrc.i());
		tab.addTab("Gyem src",(JComponent) explorer_gyemSrc.i());
	}
	
	
	
	public Object i() throws Exception
	{return tab;}
}
