package a.entity.gus06.jdbc.gui.cx1.db.table;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20150622";}
	
	public static final int POSITION = 300;


	private Service list;
	private Service gui;
	
	private JSplitPane split;
	
	private G holder;
	private String dbName;
	
	
	public EntityImpl() throws Exception
	{
		list = Outside.service(this,"*gus06.jdbc.gui.cx1.db.table.list");
		gui = Outside.service(this,"*gus06.jdbc.gui.cx1.db.table.gui");
		
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
		if(obj==null)
		{
			holder = null;
			dbName = null;
			resetGui();
			return;
		}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		holder = (G) o[0];
		dbName = (String) o[1];
		updateGui();
	}
	
	
	
	private void resetGui()
	{
		try
		{
			list.p(null);
			gui.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"resetGui()",e);}
	}
	
	
	
	private void updateGui()
	{
		try
		{
			list.p(new Object[]{holder,dbName});
			gui.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}




	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			String selected = (String) list.g();
			
			if(holder!=null && dbName!=null && selected!=null)
			gui.p(new Object[]{holder,dbName,selected});
			else gui.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
