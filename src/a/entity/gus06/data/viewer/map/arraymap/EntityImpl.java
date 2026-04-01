package a.entity.gus06.data.viewer.map.arraymap;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.table.AbstractTableModel;


public class EntityImpl implements Entity, P, I, R {

	public String creationDate() {return "20201014";}
	

	private JPanel panel;
	private TableModel0 model;
	private JTable table;
	private JLabel label;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		model = new TableModel0();
		
		table = new JTable(model);
		table.getTableHeader().setReorderingAllowed(false);
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		updateLabel();
		SwingUtilities.invokeLater(model);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("table")) return table;
		if(key.equals("keys")) return new String[]{"table"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	private void updateLabel()
	{
		if(map==null) label.setText(" ");
		else label.setText(" Number: "+map.size());
	}
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile ArrayList keys;
        
		public TableModel0()
		{keys = new ArrayList();}
    	
		public int getRowCount(){return keys.size();}
		public int getColumnCount(){return 2;}
    	
		public Class getColumnClass(int y){return String.class;}
		public String getColumnName(int y){return y==0?"key":"count";}
    	
		public Object getValueAt(int x, int y)
		{
			Object key = keys.get(x);
			if(y==0)return key;
			String[] array = (String[]) map.get(key);
			return Integer.valueOf(array.length);
		}

		public void run()
		{
			keys = map!=null?new ArrayList(map.keySet()):new ArrayList();
			Collections.sort(keys);
			fireTableDataChanged();
		}
	}
	

}
