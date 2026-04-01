package a.entity.gus06.sys.xhtml1.ids.gui.panel;

import a.framework.*;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, I, P, E {

	public String creationDate() {return "20220908";}

	
	private Service tableHolder;
	private Service custSplit;
	private Service detailHolder;
	
	private JSplitPane split;
	

	public EntityImpl() throws Exception
	{
		tableHolder = Outside.service(this,"*gus06.sys.xhtml1.ids.gui.table");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		detailHolder = Outside.service(this,"*gus06.sys.xhtml1.ids.gui.detail");
		
		tableHolder.addActionListener(this);
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) tableHolder.i());
		split.setRightComponent((JComponent) detailHolder.i());
		
		custSplit.p(split);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public Object g() throws Exception
	{return null;}
	
	
	public void e() throws Exception
	{}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		tableHolder.p(obj);
		detailHolder.p(null);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	public void selected()
	{
		try
		{
			Object selection = tableHolder.g();
			detailHolder.p(selection);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}