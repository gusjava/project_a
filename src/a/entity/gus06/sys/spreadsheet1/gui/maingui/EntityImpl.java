package a.entity.gus06.sys.spreadsheet1.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;
import java.io.File;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import java.util.Properties;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.SwingWorker;
import java.awt.Cursor;

public class EntityImpl implements Entity, P, I {
	public String creationDate() {return "20260102";}
	
	public static final Color SELECTION_COLOR = new Color(210,235,235);
	public static final Color PRECOMPUTED_COLOR = new Color(243,243,243);
	public static final Color DESYNCHRONIZED_COLOR = new Color(255,255,204);
	
	public static final String DATA_FILENAME = "data.properties";
	
	public static final String ROW_NB = "row_nb";
	public static final String COLUMN_NB = "column_nb";
	public static final String COLUMN_NAME = "column_title";
	public static final String COLUMN_SIZE = "column_size";
	public static final String AUTOCOMMIT = "autocommit";

	private Service readProp;
	private Service writeProp;
	private Service buildEditableTable;
	private Service completeValueTable0;
	private Service completeValueTable1;
	private Service clearCopyPasteCut;
	private Service tableTooltip;
	private Service clipboard;
	private Service autoScroll;
	
	private JPanel panel;
	private TableModel0 model;
	private JTable table;
	private JLabel label;
	private JScrollPane scroll;
	
	private Map map;
	private File root;
	
	private File dataFile;
	private Map dataMap;
	
	private int[] dim;
	private int[] sizes;
	private boolean autoCommit = true;
	
	private String[][] valueTable;
	private boolean[][] editableTable;
	private String errMsg;
	private boolean isPending = false;
	private boolean needsF5 = false;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus.x.file.prop.read");
		writeProp = Outside.service(this,"gus06.file.write.properties");
		buildEditableTable = Outside.service(this,"gus06.sys.spreadsheet1.build.editabletable");
		completeValueTable0 = Outside.service(this,"gus06.sys.spreadsheet1.valuetable.complete0");
		completeValueTable1 = Outside.service(this,"gus06.sys.spreadsheet1.valuetable.complete1");
		clearCopyPasteCut = Outside.service(this, "gus.x.swing.comp.action.clear.copypastecut");
		tableTooltip = Outside.service(this, "gus.x.swing.table.cust.tooltip1");
		clipboard = Outside.service(this,"gus.x.clipboard.string");
		autoScroll = Outside.service(this,"gus.x.swing.scroll.autoposition1");
		
		model = new TableModel0();
		table = new JTable(model);
		table.setDefaultRenderer(String.class, new TableCellRenderer0());
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int code = e.getKeyCode();
				if (e.isControlDown())
				{
					if (code == KeyEvent.VK_C) performCtrlC();
					else if (code == KeyEvent.VK_V) performCtrlV();
					else if (code == KeyEvent.VK_X) performCtrlX();
				} else {
					if (code == KeyEvent.VK_DELETE) performDel();
					else if (code == KeyEvent.VK_F1) performF1();
					else if (code == KeyEvent.VK_F2) performF2();
					else if (code == KeyEvent.VK_F3) performF3();
					else if (code == KeyEvent.VK_F5) performF5();
				}
			}
		});

		clearCopyPasteCut.p(table);
		tableTooltip.p(table);
		
		label = new JLabel(" ");
		
		scroll = new JScrollPane(table);
		autoScroll.p(scroll);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(label, BorderLayout.SOUTH);
	}
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		reset();
		if(obj==null) return;
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		map = (Map) o[0];
		root = (File) o[1];
		
		startPending();
		new InitWorker().execute();
	}
	
	private void reset()
	{
		map = null;
		root = null;
		dataFile = null;
		dataMap = null;
		dim = null;
		valueTable = null;
		editableTable = null;
		errMsg = null;
		
		model.fireTableStructureChanged();
		label.setText(" ");
	}
	
	private void buildEditableTable() throws Exception
	{
		editableTable = (boolean[][]) buildEditableTable.t(new Object[]{dim, map});
	}
	
	private void buildValueTable(boolean commit) throws Exception
	{
		if(commit)
		{
			valueTable = new String[dim[0]][dim[1]];
			completeValueTable0.p(new Object[]{valueTable, editableTable, dataMap});
			errMsg = (String) completeValueTable1.t(new Object[]{valueTable, map});
			needsF5 = false;
			
			if(errMsg!=null)
			{
				label.setText(errMsg);
				label.setForeground(Color.RED);
			}
			else
			{
				label.setText(" ");
				label.setForeground(Color.BLACK);
			}
		}
		else
		{
			completeValueTable0.p(new Object[]{valueTable, editableTable, dataMap});
			needsF5 = true;
			label.setText(" ");
			label.setForeground(Color.BLACK);
		}
	}
	
	private boolean has(String key)
	{return map!=null && map.containsKey(key);}
	
	private String getString(String key)
	{return has(key) ? (String) map.get(key) : null;}
	
	private int getInt(String key)
	{return getInt(key, 0);}
	
	private int getInt(String key, int defaultValue)
	{
		String val = getString(key);
		return val!=null ? Integer.parseInt(val) : defaultValue;
	}
	
	private boolean getBoolean(String key)
	{return getBoolean(key, false);}
	
	private boolean getBoolean(String key, boolean defaultValue)
	{
		String val = getString(key);
		return val!=null ? Boolean.parseBoolean(val) : defaultValue;
	}
	
	private void startPending()
	{
		isPending = true;
		label.setForeground(Color.GRAY);
		label.setText("Pending...");
	}
	
	private void setData(int x, int y, String data)
	{
		if(dataMap==null) return;
		if(!table.isCellEditable(x,y)) return;
		
		if(data==null) data = "";
		if(data.equals(table.getValueAt(x,y))) return;
		
		startPending();
		new SetDataWorker(x, y, data).execute();
	}
	
	
	private void resizeColumns()
	{
		if(dim==null) return;
		for(int i=0;i<dim[1];i++)
		{
			int width = getInt(COLUMN_SIZE+"_"+i, -1);
			if(width!=-1) resizeColumns(i, width);
		}
	}
	private void resizeColumns(int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	

	private class TableModel0 extends AbstractTableModel
	{
		public int getRowCount()
		{return dim!=null ? dim[0] : 0;}
		
		public int getColumnCount()
		{return dim!=null ? dim[1] : 0;}

		public Class getColumnClass(int y)
		{return String.class;}

		public String getColumnName(int y)
		{return getString(COLUMN_NAME+"_"+y);}

		public boolean isCellEditable(int x, int y)
		{
			if(isPending) return false;
			return editableTable!=null ? editableTable[x][y] : false;
		}

		public Object getValueAt(int x, int y)
		{
			if(isPending) return "...";
			return valueTable!=null ? valueTable[x][y] : null;
		}
		
		public void setValueAt(Object value, int x, int y)
		{
			if(isPending) return;
			setData(x,y,(String) value);
		}
	}
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
    			setBackground(Color.WHITE);
    		}

		public Component getTableCellRendererComponent(JTable table, Object value, 
		boolean isSelected, boolean hasFocus, int row, int column)
		{
			String key = (String) value;
			setText(key);
			setIcon(null);
			setBackground(findBackground(isSelected, row, column));
			return this;
		}
	}
	
	private Color findBackground(boolean isSelected, int row, int column)
	{
		if(isSelected) return SELECTION_COLOR;
		if(!table.isCellEditable(row,column)) 
			return needsF5 ? DESYNCHRONIZED_COLOR : PRECOMPUTED_COLOR;
		return Color.WHITE;
	}
	
	private String getSelectedCellValue()
	{
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1) return null;
		return (String) table.getValueAt(row, col);
	}

	
	private void performCtrlC()
	{
		try
		{
			String val = getSelectedCellValue();
			if(val!=null) clipboard.p(val);
		}
		catch(Exception e)
		{Outside.err(this,"performCtrlC()",e);}
	}
	
	private void performCtrlV()
	{
		try
		{
			if(isPending) return;	
			int row = table.getSelectedRow();
    			int col = table.getSelectedColumn();
			if(row == -1 || col == -1) return;
			if(!table.isCellEditable(row,col)) return;
			
			String s = (String) clipboard.g();
			if(s==null) return;
			
			setData(row,col,s);
		}
		catch(Exception e)
		{Outside.err(this,"performCtrlV()",e);}
	}
	
	private void performCtrlX()
	{
		try
		{
			if(isPending) return;
			int row = table.getSelectedRow();
    			int col = table.getSelectedColumn();
			if(row == -1 || col == -1) return;
			if(!table.isCellEditable(row,col)) return;
			
			String val = (String) table.getValueAt(row, col);
			clipboard.p(val);
			
			setData(row,col,"");
		}
		catch(Exception e)
		{Outside.err(this,"performCtrlX()",e);}
	}

	private void performDel()
	{
		try
		{
			if(isPending) return;
			int row = table.getSelectedRow();
    			int col = table.getSelectedColumn();
			if(row == -1 || col == -1) return;
			if(!table.isCellEditable(row,col)) return;
			
			setData(row,col,"");
		}
		catch(Exception e)
		{Outside.err(this,"performDel()",e);}
	}

	private void performF1()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"performF1()",e);}
	}

	private void performF2()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"performF2()",e);}
	}

	private void performF3()
	{
		try
		{
			
		}
		catch(Exception e)
		{Outside.err(this,"performF3()",e);}
	}

	private void performF5()
	{
		startPending();
		new InitWorker().execute();
	}


	private class SetDataWorker extends SwingWorker<Void, Void>
	{
		private final int x;
		private final int y;
		private final String data;
	
		public SetDataWorker(int x, int y, String data)
		{
			this.x = x;
			this.y = y;
			this.data = data;
		}
	
		protected Void doInBackground() throws Exception
		{
			String key = x + "," + y;
			if(data == null) dataMap.remove(key);
			else dataMap.put(key, data);
			writeProp.p(new Object[]{dataFile, dataMap});
			buildValueTable(autoCommit);
			return null;
		}
	
		protected void done()
		{
			try
			{
				get();
				isPending = false;
				model.fireTableDataChanged();
			}
			catch(Exception e)
			{
				Outside.err(EntityImpl.this, "SetDataWorker.done()", e);
			}
		}
	}
	
	private class InitWorker extends SwingWorker<Void, Void>
	{
		protected Void doInBackground() throws Exception
		{
			dataFile = new File(root,DATA_FILENAME);
		
			dataMap = (Map) readProp.t(dataFile);
			if(dataMap==null) dataMap = new Properties();
			
			dim = new int[2];
			dim[0] = getInt(ROW_NB);
			dim[1] = getInt(COLUMN_NB);
			
			autoCommit = getBoolean(AUTOCOMMIT, true);
		
			buildEditableTable();
			buildValueTable(true);
			return null;
		}
	
		protected void done()
		{
			try
			{
				get();
				isPending = false;
				model.fireTableStructureChanged();
				resizeColumns();
			}
			catch(Exception e)
			{
				Outside.err(EntityImpl.this, "InitWorker.done()", e);
			}
		}
	}
}
