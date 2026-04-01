package a.entity.gus06.appli.gusclient1.gui.appdoc.entities.viewer;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140828";}


	private Service selector;
	private Service viewer;
	private Service splitCust;

	private JSplitPane split;
	
	public EntityImpl() throws Exception
	{
		selector = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.entities.viewer.selector");
		viewer = Outside.service(this,"*gus06.app.jarfile.entity.viewer");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent((JComponent) selector.i());
		split.setRightComponent((JComponent) viewer.i());

		selector.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{entitySelected();}



	private void entitySelected()
	{
		try
		{
			String name = (String) selector.g();
			viewer.p(name);
		}
		catch(Exception e)
		{Outside.err(this,"entitySelected()",e);}
	}
}
