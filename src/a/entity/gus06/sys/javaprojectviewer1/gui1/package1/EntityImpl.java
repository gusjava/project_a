package a.entity.gus06.sys.javaprojectviewer1.gui1.package1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20170220";}
	
	public static final int POSITION = 400;
	
	
	private Service list;
	private Service table;
	
	private JSplitPane split;
	
	private Object data;
	
	
	
	public EntityImpl() throws Exception
	{
		list = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.package1.list");
		table = Outside.service(this,"*gus06.sys.javaprojectviewer1.gui1.class1");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(POSITION);

		split.setLeftComponent((JComponent) list.i());
		split.setRightComponent((JComponent) table.i());
		
		list.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		list.p(data);
		table.p(null);
	}
	
	



	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			Object selected = list.g();
			table.p(selected);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
