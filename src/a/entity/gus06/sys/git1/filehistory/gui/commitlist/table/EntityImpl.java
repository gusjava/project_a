package a.entity.gus06.sys.git1.filehistory.gui.commitlist.table;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JTable;
import java.util.Date;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import java.util.Map;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class EntityImpl extends S1 implements Entity, ListSelectionListener, I, P, G {

	public String creationDate() {return "20201129";}


	private Service tableTooltip;
	private Service tableRenderer;
	
	private TableModel0 model;
	private JTable table;
	
	private List commits;


	public EntityImpl() throws Exception
	{
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		tableRenderer = Outside.service(this,"gus06.sys.git1.filehistory.gui.commitlist.renderer");
		
		model = new TableModel0();
		
		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		
		tableTooltip.p(table);
		tableRenderer.p(table);
		
		initColumnSize(0,30);
		initColumnSize(1,35);
		initColumnSize(2,120);
		initColumnSize(3,120);
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public Object g() throws Exception
	{
		if(commits==null) return null;
		int row = table.getSelectedRow();
		return row!=-1 ? commits.get(row) : null;
	}
	
	
	public void p(Object obj) throws Exception
	{
		commits = (List) obj;
		model.fireTableDataChanged();
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				if(!commits.isEmpty())
				table.setRowSelectionInterval(0,0);
			}
		});
	}
	
	
	public void valueChanged(ListSelectionEvent e) 
	{selected();}
	
	
	private void selected()
	{send(this,"selected()");}
	
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getRowCount(){return commits!=null ? commits.size() : 0;}
		public int getColumnCount(){return 5;}
    	
		public Class getColumnClass(int y)
		{
			if(y==0) return Integer.class;
			if(y==1) return String.class;
			if(y==2) return Date.class;
			if(y==3) return String.class;
			return String.class;
		}
		
		public String getColumnName(int y)
		{
			if(y==0) return "I";
			if(y==1) return "State";
			if(y==2) return "Time";
			if(y==3) return "Author";
			return "Message";
		}
    	
		public Object getValueAt(int x, int y)
		{
			Map m = (Map) commits.get(x);
			
			if(y==0) return m.get("index");
			if(y==1) return m.get("state");
			if(y==2) return m.get("time");
			if(y==3) return m.get("author");
			return m.get("message");
		}
	}
}