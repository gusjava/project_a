package a.entity.gus06.sys.filetool.ext.repartition1.gui.table;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.io.File;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalLabelUI;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Font;


public class EntityImpl implements Entity, I, V, E {

	public String creationDate() {return "20151021";}


	private File[] files;
	private Color[] colors;
	private Map map;

	private JTable table;
	private TableModel0 model;
	


	public EntityImpl() throws Exception
	{
		map = new HashMap();
		files = new File[0];
		
		model = new TableModel0();
		table = new JTable(model);
		
		table.setDefaultRenderer(File.class, new TableCellRendererFile());
		table.setDefaultRenderer(int[].class, new TableCellRendererIntArray());
	}
	
	
	public Object i() throws Exception
	{return table;}
	
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("files"))		{files = (File[]) obj;return;}
		if(key.equals("colors"))	{colors = (Color[]) obj;return;}
		if(key.equals("map"))		{map = (Map) obj;return;}
		
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void e() throws Exception
	{
		model.fireTableDataChanged();
	}
	
	
	private Color getColor(int i)
	{
		if(colors==null || colors.length<=i || i<0) return null;
		return colors[i];
	}
	
	
	
	
	
	
	private class TableModel0 extends AbstractTableModel
	{
		public int getColumnCount() {return 2;}
		public int getRowCount() {return files.length;}
		
		public String getColumnName(int y)
		{
			if(y==0)return "Key";
			return "Repartition";
		}
		
		public Class getColumnClass(int y)
		{
			if(y==0) return File.class;
			return int[].class;
		}
		
		public boolean isCellEditable(int x, int y)
		{return false;}

		public Object getValueAt(int x, int y)
		{
			if(y==0) return files[x];
			Map m = (Map) map.get(files[x]);
			return m.get("values");
		}
	}
	
	
	
	private class TableCellRendererFile extends JLabel implements TableCellRenderer
	{
		private Font font_p;
		private Font font_f;
		
		public TableCellRendererFile()
		{
			super();
			font_f = getFont().deriveFont(Font.PLAIN);
			font_p = getFont().deriveFont(Font.BOLD);
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			File file = (File) value;
			Map m = (Map) map.get(file);
			Boolean same = (Boolean) m.get("same");
			
			setText(file.getName());
			setFont(same.booleanValue() ? font_p : font_f);
			
			return this;
		}
	}
	
	
	
	private class TableCellRendererIntArray extends JLabel implements TableCellRenderer
	{
		ColorLabelUI ui;
		
		TableCellRendererIntArray()
		{
			super();
			ui = new ColorLabelUI();
			setUI(ui);
		}
		
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			int[] a = (int[]) value;
			ui.setData(a);
			return this;
		}
	}
	
	
	
	private class ColorLabelUI extends MetalLabelUI
	{
		private int[] a;
		private int size;
		
		public void setData(int[] a)
		{
			this.a = a;
			size = a.length;
		}
		
		public void paint(Graphics g, JComponent c)
		{
			super.paint(g,c);
			if(size==0) return;
			
			Insets b = c.getInsets();
			Rectangle r = c.getBounds();
			
			int h = r.height;
			int w = r.width;
            
			int y0 = b.top;
			int y1 = h - b.bottom;
			double dx = (double)w / (double)size;
			
			for(int i=0;i<size;i++)
			{
				int value = a[i];
				Color color = getColor(value);
				
				if(color!=null)
				{
					g.setColor(color);
					int x0 = (int) (i*dx + b.left);
					int x1 = (int) ((i+1)*dx + b.left);
					
					g.fillRect(x0,y0,x1-x0,y1-y0);
				}
			}
		}
	}
}