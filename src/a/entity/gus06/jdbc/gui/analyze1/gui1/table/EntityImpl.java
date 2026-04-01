package a.entity.gus06.jdbc.gui.analyze1.gui1.table;

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

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230226";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	
	public static final String TYPE_BOOLEAN = "BOOLEAN";
	public static final String TYPE_INTEGER = "INTEGER";
	public static final String TYPE_LONG = "LONG";
	public static final String TYPE_DOUBLE = "DOUBLE";
	public static final String TYPE_DATE = "DATE";
	public static final String TYPE_STRING = "STRING";
	public static final String TYPE_LSTRING = "LSTRING";


	private Service typeToColor;
	private Service tooltip;
	private Service showTableAnalyze;
	private Service onKey;
	
	private Icon icon;

	private JTable table;
	
	private TableModel0 model;

	private Connection cx;
	private String dbName;
	private Map map = new HashMap();
	private List keys = new ArrayList();



	public EntityImpl() throws Exception
	{
		typeToColor = Outside.service(this,"gus06.jdbc.gui.analyze1.tool.datatypetocolor");
		tooltip = Outside.service(this,"gus06.swing.table.cust.tooltip2");
		showTableAnalyze = Outside.service(this,"gus06.jdbc.gui.cx1.db.table.list.control.analyze");
		onKey = Outside.service(this,"gus06.swing.comp.cust3.on.keypressed.with.execute");
		
		icon = (Icon) Outside.resource(this,"icon#TABLE");
		
		model = new TableModel0();
		table = new JTable(model);
		
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		table.setShowGrid(false);
		
		E exeAnalyze = (E) this::showTableAnalyze;
		onKey.p(new Object[]{table, "F4", exeAnalyze});
		
		TableCellRenderer1 renderer = new TableCellRenderer1();
		table.setDefaultRenderer(Object.class, renderer);
		table.setDefaultRenderer(String.class, renderer);
		table.setDefaultRenderer(Integer.class, renderer);
		
		tooltip.p(table);
		
		resizeColumns(1,60);
		resizeColumns(2,60);
		resizeColumns(3,120);
		resizeColumns(4,50);
		resizeColumns(5,50);
		resizeColumns(6,50);
		resizeColumns(7,50);
		resizeColumns(8,50);
		resizeColumns(9,50);
		resizeColumns(10,50);
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		cx = (Connection) o[0];
		dbName = (String) o[1];
		map = (Map) o[2];
		
		keys = new ArrayList(map.keySet());
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
		public int getColumnCount(){return 11;}
    	
		public boolean isCellEditable(int x, int y)
		{return false;}
		
		public Class getColumnClass(int y)
		{
			if(y==0) return String.class;
			if(y==1) return Integer.class;
			if(y==2) return Integer.class;
			if(y==3) return Object.class;
			if(y==4) return Integer.class;
			if(y==5) return Integer.class;
			if(y==6) return Integer.class;
			if(y==7) return Integer.class;
			if(y==8) return Integer.class;
			if(y==9) return Integer.class;
			if(y==10) return Integer.class;
			return null;
		}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Name";
			if(y==1) return "Cols";
			if(y==2) return "Rows";
			if(y==3) return "Types";
			if(y==4) return "NN";
			if(y==5) return "PK";
			if(y==6) return "UK";
			if(y==7) return "FK";
			if(y==8) return "refFK";
			if(y==9) return "FK0";
			if(y==10) return "FK0E";
			return null;
		}
		
		public Object getValueAt(int x, int y)
		{
			String tableName = (String) keys.get(x);
			if(y==0) return tableName;
			
			R holder = (R) map.get(tableName);
			if(y==1) return find(holder,"nbCol");
			if(y==2) return find(holder,"nbRow");
			if(y==3) return findTypes(holder);
			if(y==4) return find(holder,"nbNN");
			if(y==5) return find(holder,"nbPk");
			if(y==6) return find(holder,"nbUk");
			if(y==7) return find(holder,"nbFk");
			if(y==8) return find(holder,"nbRefFk");
			if(y==9) return find(holder,"nbFk0");
			if(y==10) return find(holder,"nbFk0e");
			return null;
		}
	}
	
	
	private Object find(R holder, String key)
	{
		try{return holder.r(key);}
		catch(Exception e)
		{return "###"+e;}
	}
	
	private Object findTypes(R holder)
	{
		try
		{
			Integer nbBoolean = (Integer) holder.r("nbBoolean");
			Integer nbInteger = (Integer) holder.r("nbInteger");
			Integer nbDouble = (Integer) holder.r("nbDouble");
			Integer nbLong = (Integer) holder.r("nbLong");
			Integer nbDate = (Integer) holder.r("nbDate");
			Integer nbString = (Integer) holder.r("nbString");
			Integer nbLString = (Integer) holder.r("nbLString");
			
			return new int[]{
				nbBoolean, 
				nbInteger, 
				nbDouble, 
				nbLong, 
				nbDate, 
				nbString,
				nbLString
			};
		}
		catch(Exception e)
		{return "###"+e;}
	}
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private JPanel panelTypes;
		
		private JLabel labelNbBoolean;
		private JLabel labelNbInteger;
		private JLabel labelNbLong;
		private JLabel labelNbDouble;
		private JLabel labelNbDate;
		private JLabel labelNbLString;
		private JLabel labelNbString;
		
		
		public TableCellRenderer1() throws Exception
		{
			super();
			setOpaque(true);
			setBackground(Color.WHITE);
			
			labelNbBoolean = colorLabel(TYPE_BOOLEAN);
			labelNbInteger = colorLabel(TYPE_INTEGER);
			labelNbDouble = colorLabel(TYPE_DOUBLE);
			labelNbLong = colorLabel(TYPE_LONG);
			labelNbDate = colorLabel(TYPE_DATE);
			labelNbString = colorLabel(TYPE_STRING);
			labelNbLString = colorLabel(TYPE_LSTRING);
	
			panelTypes = new JPanel(new GridLayout(1,7));
			panelTypes.setBackground(Color.WHITE);
			
			panelTypes.add(labelNbBoolean);
			panelTypes.add(labelNbInteger);
			panelTypes.add(labelNbDouble);
			panelTypes.add(labelNbLong);
			panelTypes.add(labelNbDate);
			panelTypes.add(labelNbString);
			panelTypes.add(labelNbLString);
		}
		
		private JLabel colorLabel(String type) throws Exception
		{
			Color c = (Color) typeToColor.t(type);
			JLabel label = new JLabel(" ");
			label.setForeground(c);
			return label;
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			setIcon(icon(column));
			if(value==null)
			{
				setText("null");
				setHorizontalAlignment(JLabel.LEFT);
				setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
				return this;
			}
			
			if(value instanceof String)
			{
				setText(" "+value);
				setHorizontalAlignment(JLabel.LEFT);
				setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
				return this;
			}
			
			if(value instanceof Integer)
			{
				Integer v = (Integer) value;
				setText(intToString(v,"")+" ");
				setHorizontalAlignment(JLabel.RIGHT);
				setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
				return this;
			}
			
			if(value instanceof int[])
			{
				int[] types = (int[]) value;
				
				labelNbBoolean.setText(intToString(types[0],"_"));
				labelNbInteger.setText(intToString(types[1],"_"));
				labelNbDouble.setText(intToString(types[2],"_"));
				labelNbLong.setText(intToString(types[3],"_"));
				labelNbDate.setText(intToString(types[4],"_"));
				labelNbString.setText(intToString(types[5],"_"));
				labelNbLString.setText(intToString(types[6],"_"));
				
				panelTypes.setBackground(isSelected ? SELECTION_COLOR : Color.WHITE);
				return panelTypes;
			}
			
			return this;
		}
	}
	
	private String intToString(int n, String defaultValue)
	{return n!=0 ? ""+n : defaultValue;}
	
	
	private Icon icon(int column)
	{return column==0 ? icon : null;}
	
	
	
	private void showTableAnalyze()
	{
		try
		{
			int row = table.getSelectedRow();
			if(row==-1) return;
			
			String tableName = (String) table.getValueAt(row,0);
			showTableAnalyze.p(new Object[]{cx, dbName, tableName});
		}
		catch(Exception e)
		{Outside.err(this,"showTableAnalyze()",e);}
	}
}