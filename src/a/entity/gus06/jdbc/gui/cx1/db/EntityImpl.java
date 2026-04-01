package a.entity.gus06.jdbc.gui.cx1.db;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20150622";}
	
	public static final int POSITION = 300;


	private Service list;
	private Service table;

	private JSplitPane split;
	
	private Object holder;
	
	
	public EntityImpl() throws Exception
	{
		list = Outside.service(this,"*gus06.jdbc.gui.cx1.db.list");
		table = Outside.service(this,"*gus06.jdbc.gui.cx1.db.table");
		
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
		holder = obj;
		
		list.p(holder);
		table.p(null);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("dumpHolder")) {list.v("dumpHolder", obj);return;}
		throw new Exception("Unknown key: "+key);
	}


	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	
	private void selectionChanged()
	{
		try
		{
			List selected = (List) list.g();
			
			if(selected!=null && !selected.isEmpty())
			table.p(new Object[]{holder, selected.get(0)});
			else table.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}
