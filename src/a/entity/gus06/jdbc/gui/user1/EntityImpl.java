package a.entity.gus06.jdbc.gui.user1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20150625";}
	
	public static final int POSITION = 300;


	private Service list;
	private Service gui;

	private JSplitPane split;
	
	private G holder;
	

	public EntityImpl() throws Exception
	{
		list = Outside.service(this,"*gus06.jdbc.gui.user1.list");
		gui = Outside.service(this,"*gus06.jdbc.gui.user1.gui");
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(POSITION);

		split.setLeftComponent((JComponent) list.i());
		split.setRightComponent((JComponent) gui.i());
		
		list.addActionListener(this);
	}
	
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		holder = (G) obj;
		
		list.p(holder);
		gui.p(null);
	}


	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			String selected = (String) list.g();
			
			if(selected!=null)
			gui.p(new Object[]{holder,selected});
			else gui.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
