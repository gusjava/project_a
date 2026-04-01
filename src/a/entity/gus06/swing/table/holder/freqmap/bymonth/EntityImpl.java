package a.entity.gus06.swing.table.holder.freqmap.bymonth;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JTable;
import java.util.Map;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Color;

public class EntityImpl implements Entity, V, I, P {

	public String creationDate() {return "20191208";}
	
	public static final Color COLOR = new Color(196,216,169);
	public static final String COL1 = "month";
	public static final String COL2 = "count";


	private Service tableSort;
	private Service tableTooltip;
	private Service tableRenderer;
	private Service rebuild;

	private TableModel1 model;
	private Object renderer;
	private JTable table;
	
	private Map freq;
	private List keys;
	
	private Color color = COLOR;
	private String col1 = COL1;
	private String col2 = COL2;

	public EntityImpl() throws Exception
	{
		tableSort = Outside.service(this,"gus06.swing.table.cust.sort1");
		tableTooltip = Outside.service(this,"gus06.swing.table.cust.tooltip1");
		tableRenderer = Outside.service(this,"gus06.swing.table.cust.renderer.freqmap");
		rebuild = Outside.service(this,"gus06.time.timestamp.yyyymm.buildrange");
		
		model = new TableModel1();
		table = new JTable(model);
		
		tableSort.p(table);
		tableTooltip.p(table);
		
		renderer = tableRenderer.t(table);
		((V)renderer).v("color",COLOR);
		
		resizeColumns();
	}
	
	private void resizeColumns()
	{
		table.getColumnModel().getColumn(0).setMinWidth(80);
		table.getColumnModel().getColumn(0).setMaxWidth(80);
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public void p(Object obj) throws Exception
	{
		freq = (Map) obj;
		if(freq==null) keys = null;
		else
		{
			keys = (List) rebuild.t(freq.keySet());
			Collections.reverse(keys);
		}
		model.fireTableStructureChanged();
		resizeColumns();
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("color"))
		{
			color = (Color) obj;
			((V)renderer).v("color",color);
			return;
		}
		if(key.equals("col1"))
		{
			col1 = (String) obj;
			model.fireTableStructureChanged();
			return;
		}
		if(key.equals("col2"))
		{
			col2 = (String) obj;
			model.fireTableStructureChanged();
			return;
		}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	
	private class TableModel1 extends AbstractTableModel
	{
		public int getRowCount()
		{return keys==null?0:keys.size();}
		
		public int getColumnCount()
		{return 2;}
		
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{return y==0?String.class:Integer.class;}
		
		public String getColumnName(int y)
		{return y==0?col1:col2;}
		
		public Object getValueAt(int x, int y)
		{
			String key = (String) keys.get(x);
			return y==0?formatKey(key):getFreq(key);
		}
	}
	
	
	
	private Integer getFreq(String key)
	{
		if(!freq.containsKey(key)) return 0;
		return (Integer) freq.get(key);
	}
	
	
	private String formatKey(String key)
	{return key.substring(0,4)+"."+key.substring(4,6);}
}
