package a.entity.gus06.appli.gusclient1.gui.appdoc.manager.viewer.modules2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140917";}


	private Service tree;
	private Service viewer;
	private Service splitCust;

	private JSplitPane split;
	
	public EntityImpl() throws Exception
	{
		tree = Outside.service(this,"*gus06.appli.gusclient1.gui.appdoc.manager.viewer.modules2.tree");
		viewer = Outside.service(this,"*gus06.app.jarfile.classpath.viewer");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent((JComponent) tree.i());
		split.setRightComponent((JComponent) viewer.i());
		
		split.setDividerLocation(200);

		tree.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void actionPerformed(ActionEvent e)
	{selected();}



	private void selected()
	{
		try{viewer.p(tree.g());}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
