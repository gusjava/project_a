package a.entity.gus06.appli.gusclient1.gui.space.documentation.tuto;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140818";}


	private Service splitCust;
	
	private JSplitPane split;
	
	public EntityImpl() throws Exception
	{
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		split = new JSplitPane();
		splitCust.p(split);
	}
	
	
	public Object i() throws Exception
	{return split;}
}
