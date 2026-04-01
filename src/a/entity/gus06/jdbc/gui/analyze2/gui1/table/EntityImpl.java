package a.entity.gus06.jdbc.gui.analyze2.gui1.table;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.ListSelectionModel;
import java.sql.Connection;
import javax.swing.Icon;
import java.awt.Font;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230227";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";
	
	public static final String COL1_DB = "TABLE_SCHEMA";
	public static final String COL1_TABLE = "TABLE_NAME";
	public static final String COL1_COLUMN_NAME = "COLUMN_NAME";
	public static final String COL1_COLUMN_TYPE = "COLUMN_TYPE";
	public static final String COL1_COLUMN_KEY = "COLUMN_KEY";
	public static final String COL1_IS_NULLABLE = "IS_NULLABLE";
	public static final String COL1_EXTRA = "EXTRA";
	
	public static final String COL1_TYPE = "TYPE";
	public static final String COL1_AUTOINCR = "AUTOINCR";
	public static final String COL1_NN = "NN";
	public static final String COL1_PK = "PK";
	public static final String COL1_UK = "UK";
	public static final String COL1_FK = "FK";
	public static final String COL1_FK0 = "FK0";
	public static final String COL1_FK0E = "FK0E";


	private Service tooltip;
	private Service typeToColor;
	private Icon iconColumn;
	
	
	private JTable table;
	
	private TableModel0 model;

	private Connection cx;
	private String dbName;
	private String tableName;
	private Object holder;
	
	private List keys = new ArrayList();
	private Map mapCol = new HashMap();



	public EntityImpl() throws Exception
	{
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		typeToColor = Outside.service(this,"gus06.jdbc.gui.analyze1.tool.datatypetocolor");
		iconColumn = (Icon) Outside.resource(this,"icon#COLUMN");
		
		model = new TableModel0();
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		table.setShowGrid(false);
		
		TableCellRenderer1 renderer = new TableCellRenderer1();
		table.setDefaultRenderer(Object.class, renderer);
		table.setDefaultRenderer(String.class, renderer);
		table.setDefaultRenderer(Integer.class, renderer);
		
		tooltip.p(table);
		
		resizeColumns(1,100);
		resizeColumns(2,30);
		resizeColumns(3,30);
		resizeColumns(4,150);
		resizeColumns(5,180);
	}
	
	
	
	private Color findColorForType(String type)
	{
		try{return (Color) typeToColor.t(type);}
		catch(Exception e){Outside.err(this,"findColorForType(String)",e);}
		return Color.BLACK;
	}
	
	private String typeAt(int row)
	{
		String colName = (String) keys.get(row);
		Map info = (Map) mapCol.get(colName);
		return (String) get(info,COL1_TYPE);
	}
	
	private boolean isAutoIncr(int row)
	{
		String colName = (String) keys.get(row);
		Map info = (Map) mapCol.get(colName);
		String autoIncr = (String) get(info,COL1_AUTOINCR);
		return autoIncr!=null && autoIncr.equals("YES");
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		cx = (Connection) o[0];
		dbName = (String) o[1];
		tableName = (String) o[2];
		holder = o[3];
		
		mapCol = (Map) ((R) holder).r("mapCol");
		keys = new ArrayList(mapCol.keySet());
		Collections.sort(keys);
		
		model.fireTableDataChanged();
	}
	
	
	
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public TableModel0(){}
    	
		public int getRowCount(){return keys.size();}
		public int getColumnCount(){return 6;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{
			if(y==0) return String.class;
			if(y==1) return String.class;
			if(y==2) return String.class;
			if(y==3) return String.class;
			if(y==4) return String.class;
			if(y==5) return Object.class;
			return null;
		}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Name";
			if(y==1) return "Type";
			if(y==2) return "NN";
			if(y==3) return "PK";
			if(y==4) return "UK";
			if(y==5) return "FK";
			return null;
		}
		
		public Object getValueAt(int x, int y)
		{
			String colName = (String) keys.get(x);
			if(y==0) return colName;
			
			Map info = (Map) mapCol.get(colName);
			if(y==1) return get(info,COL1_COLUMN_TYPE);
			if(y==2) return get(info,COL1_NN);
			if(y==3) return get(info,COL1_PK);
			if(y==4) return get(info,COL1_UK);
			if(y==5) return formatFK(info);
			return null;
		}
		
		private Object formatFK(Map info)
		{
			String[] fk = (String[]) get(info, COL1_FK);
			if(fk!=null) return new Object[]{fk[0], fk[1]};
			
			String[] fk0 = (String[]) get(info, COL1_FK0);
			if(fk0!=null) 
			{
				Integer count = (Integer) get(info, COL1_FK0E);
				return new Object[]{fk0[0], fk0[1], count};
			}
			return null;
		}
	}
	
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private Font fontP;
		private Font fontB;
		
		public TableCellRenderer1()
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			
			fontP = getFont().deriveFont(Font.PLAIN);
			fontB = getFont().deriveFont(Font.BOLD);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setFont(font(row));
			setIcon(icon(row, column, value));
			setForeground(foreground(row, column, value));
			setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
			setText(text(row, column, value));
			
			return this;
		}
		
		private Font font(int row)
		{
			return isAutoIncr(row) ? fontB : fontP;
		}
	
		private Icon icon(int row, int column, Object value)
		{
			if(column==0) return iconColumn;
			return null;
		}
		
		private Color foreground(int row, int column, Object value)
		{
			if(column==0) return findColorForType(typeAt(row));
			return null;
		}
		
		private String text(int row, int column, Object value)
		{
			if(value==null) return "";
			if(column==2) return value.equals("YES") ? "yes" : "";
			if(column==3) return value.equals("YES") ? "yes" : "";
			if(column==4) return " "+value;
			if(column==5)
			{
				Object[] data = (Object[]) value;
				if(data.length==2) return data[0]+"@"+data[1];
				if(data.length==3) return fk0HtmlDisplay(data);
				return null;
			}
			return " "+value;
		}
		
		private String fk0HtmlDisplay(Object[] data)
		{
			String path = data[0]+"@"+data[1];
			if(data[2]!=null)
				return "<html><font color='red'>"+path+" <b>("+data[2]+")</b></font></html>";
			return "<html><font color='gray'>"+path+"</font></html>";
		}
	}
	
	
	
	private Object get(Map m, String key)
	{return m.containsKey(key) ? m.get(key) : null;}
}