package a.entity.gus06.sys.keystroke1.gui1;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.table.AbstractTableModel;
import java.util.Map;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20170513";}


	private Service manager;
	private Service tableTooltip;
	private Service toEntityName;

	private JPanel panel;
	private JTable table;
	private JScrollPane scroll;
	private TableModel1 model;
	
	private Map map;
	
	
	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.keystroke1.manager");
		tableTooltip = Outside.service(this,"gus.x.swing.table.cust.tooltip1");
		toEntityName = Outside.service(this,"gus06.convert.classtoentityname");
		
		map = (Map) manager.g();
		if(map.containsKey(null)) throw new Exception("Invalid map from gus.sys.keystroke1.manager: contains null");
		
		model = new TableModel1();
		
		table = new JTable(model);
		table.setShowGrid(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTooltip.p(table);
		
		setColumnSize(table,1,200);
		setColumnSize(table,2,200);
		
		scroll = new JScrollPane(table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.getViewport().setOpaque(true);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		
		manager.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		private Map map1;
		private List ids1;
		
		private String id(int x)
		{return ids1!=null?(String) ids1.get(x):null;}
		
		public int getRowCount() {return map1!=null?map1.size():0;}
		public int getColumnCount() {return 3;}
		
		public Class getColumnClass(int y)
		{return String.class;}
		
		public boolean isCellEditable(int x, int y)
		{return y==2;}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Function";
			if(y==1) return "Default key";
			return "Current key";
		}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return toEntityName(id(x));
			if(y==1) return get(map1,id(x),"key0");
			return get(map1,id(x),"key1");
		}
		
		public void setValueAt(Object value, int x, int y)
		{
			set(map1,id(x),(String) value);
		}
		
		public void updateModel()
		{
			List ids = new ArrayList(map.keySet());
//			Collections.sort(ids);
			
			ids1 = ids;
			map1 = map;
			
			fireTableDataChanged();
		}
	}



	private String toEntityName(String value)
	{
		try{return (String) toEntityName.t(value);}
		catch(Exception e)
		{Outside.err(this,"toEntityName(String)",e);}
		return value;
	}


	private String get(Map m, String id, String info)
	{
		try
		{
			if(id==null) return null;
			R r = (R) m.get(id);
			return (String) r.r(info);
		}
		catch(Exception e)
		{Outside.err(this,"get(Map,String,String)",e);}
		return null;
	}
	

	private void set(Map m, String id, String newKey)
	{
		try
		{
			if(id==null) return;
			P p = (P) m.get(id);
			p.p(newKey);
		}
		catch(Exception e)
		{Outside.err(this,"set(Map,String,String)",e);}
	}
	
	


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	private void updateGui()
	{model.updateModel();}
	
	
	
	private void setColumnSize(JTable table, int index, int size)
	{
		table.getColumnModel().getColumn(index).setMaxWidth(size);
		table.getColumnModel().getColumn(index).setMinWidth(size);
	}
}