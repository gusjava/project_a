package a.entity.gus06.data.viewer.map.filemap;

import a.framework.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import javax.swing.table.TableCellRenderer;
import java.awt.Font;
import java.awt.Color;
import javax.swing.filechooser.FileSystemView;


public class EntityImpl implements Entity, P, I, R {

	public String creationDate() {return "20150312";}
	
	public static final Color COLOR_FILEID_EXIST = Color.BLACK;
	public static final Color COLOR_FILEID_NOTEXIST = Color.GRAY;
    


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
		table.setDefaultRenderer(Object.class,new ViewerCellRenderer());
		
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
		
		public Class getColumnClass(int y){return y==0?String.class:File.class;}
		public String getColumnName(int y){return y==0?"key":"file";}
		
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
	




	private class ViewerCellRenderer extends JLabel implements TableCellRenderer
	{
		public ViewerCellRenderer()
		{
			setOpaque(true);
			setBackground(Color.WHITE);
			setFont(getFont().deriveFont(Font.PLAIN));
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			File f = (File) table.getValueAt(row,1);
			setForeground(findColor(f));
			
			if(value instanceof String)
			{
				setIcon(null);
				setText(" "+value);
			}
			else if(value instanceof File)
			{
				File file = (File)value;
				setText(" "+file.getAbsolutePath());
				setIcon(findIcon(file));
			}
			return this;
		}  
	}
	
	
	
	
	
	private Color findColor(File f)
	{
		if(!f.exists()) return COLOR_FILEID_NOTEXIST;
		return COLOR_FILEID_EXIST;
	}
	
	private Icon findIcon(File f)
	{
		if(!f.exists()) return null;
		return FileSystemView.getFileSystemView().getSystemIcon(f);
	}
}
