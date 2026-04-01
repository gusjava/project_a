package a.entity.gus06.sys.countmap1.gui.table;

import a.framework.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.table.AbstractTableModel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;


public class EntityImpl extends S1 implements Entity, ListSelectionListener, P, G, I, R {

	public String creationDate() {return "20201027";}
	
	public static final Color COLOR_SELECTION = new Color(153,204,255);


	private Service tableSort;
	private Service tableTooltip;
	

	private JPanel panel;
	private TableModel0 model;
	private TableCellRenderer1 renderer;
	private JTable table;
	
	private Map map;
	
	
	
	public EntityImpl() throws Exception
	{
		tableSort = Outside.service(this,"gus06.swing.table.cust.sort2");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		
		model = new TableModel0();
		renderer = new TableCellRenderer1();
		
		table = new JTable(model);
		table.setGridColor(Color.WHITE);
		table.getTableHeader().setReorderingAllowed(false);
		table.setDefaultRenderer(String.class,renderer);
		table.setDefaultRenderer(Integer.class,renderer);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		
		tableSort.p(table);
		tableTooltip.p(table);
		
		initColumnSize(1,50);
	}
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public Object g() throws Exception
	{
		int row = table.getSelectedRow();
		if(row==-1) return null;
		return table.getValueAt(row,0);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
		SwingUtilities.invokeLater(model);
	}
	
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("table")) return table;
		if(key.equals("keys")) return new String[]{"table"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	
	
	
	private class TableModel0 extends AbstractTableModel implements Runnable
	{
		private volatile ArrayList keys;
        
		public TableModel0()
		{keys = new ArrayList();}
    	
		public int getRowCount(){return keys.size();}
		public int getColumnCount(){return 2;}
    	
		public Class getColumnClass(int y)
		{return y==0?String.class:Integer.class;}
		
		public String getColumnName(int y)
		{return y==0?"Name":"Count";}
    	
		public Object getValueAt(int x, int y)
		{
			Object key = keys.get(x);
			if(y==0)return key;
			return map.get(key);
		}

		public void run()
		{
			keys = map!=null?new ArrayList(map.keySet()):new ArrayList();
			Collections.sort(keys);
			fireTableDataChanged();
		}
	}
	
	
	
	
	private void initColumnSize(int index, int size)
	{
		table.getTableHeader().getColumnModel().getColumn(index).setMinWidth(size);
		table.getTableHeader().getColumnModel().getColumn(index).setMaxWidth(size);
	}
	
	
	public void valueChanged(ListSelectionEvent e) 
	{selected();}
	
	
	
	private void selected()
	{send(this,"selected()");}
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setText(""+value);
			setBackground(background(isSelected));
			return this;
		}
		
		private Color background(boolean isSelected)
		{return isSelected ? COLOR_SELECTION : Color.WHITE;}
	}
}