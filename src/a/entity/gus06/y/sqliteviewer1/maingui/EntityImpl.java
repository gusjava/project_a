package a.entity.gus06.y.sqliteviewer1.maingui;

import java.awt.BorderLayout;
import java.io.File;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.sql.Connection;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20250725";}
	
	public static final int POSITION = 300;


	private Service list;
	private Service table;


	private JSplitPane split;
	private G getCx;
	

	public EntityImpl() throws Exception
	{
		list = Outside.service(this,"*gus06.y.sqliteviewer1.gui.list");
		table = Outside.service(this,"*gus06.y.sqliteviewer1.gui.table");
		
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
		getCx = (G) obj;
		updateList();
	}
	
	
	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	private void updateList() throws Exception
	{
		list.p(getCx);
		table.p(null);
	}
	
	
	private void selectionChanged()
	{
		try
		{
			List selected = (List) list.g();
			
			if(selected!=null && !selected.isEmpty())
			table.p(new Object[]{getCx, selected.get(0)});
			else table.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
}