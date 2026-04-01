package a.entity.gus06.data.viewer.fontarray;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.JTable;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import java.awt.Component;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20190509";}

	public static final String STRING_FR = "Bonjour";
	public static final String STRING_JA = new String("\u306A\u307E\u3048");

	private Font[] data;
	private JTable table;
	private JLabel label;
	private JPanel panel;
	private FontTableModel model;

	public EntityImpl() throws Exception
	{
		model = new FontTableModel();
		
		table = new JTable(model);
		table.setDefaultRenderer(String.class,new TableCellRenderer0());
		table.setDefaultRenderer(Integer.class,new TableCellRenderer0());
		table.setDefaultRenderer(Font.class,new TableCellRendererFont());

		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Font[]) obj;
		model.fireTableDataChanged();
		
		if(data!=null)
			label.setText("Font number = "+data.length);
		else label.setText(" ");
	}
	
	
	
	
	
	private class FontTableModel extends AbstractTableModel
	{
		public int getRowCount() {return data!=null ? data.length : 0;}
		public int getColumnCount() {return 5;}
		
		public Class getColumnClass(int y)
		{
			if(y==0) return Font.class;
			if(y==1) return String.class;
			if(y==2) return Integer.class;
			if(y==3) return String.class;
			if(y==4) return String.class;
			return null;
		}
		
		public String getColumnName(int y)
		{
			if(y==0) return "Family";
			if(y==1) return "Name";
			if(y==2) return "Glyph number";
			if(y==3) return "Rendering fr";
			if(y==4) return "Rendering ja";
			return null;
		}

		public Object getValueAt(int x, int y)
		{
			if(data==null) return null;
			Font f = data[x];
			if(y==0) return f;
			if(y==1) return f.getName();
			if(y==2) return Integer.valueOf(f.getNumGlyphs());
			if(y==3) return STRING_FR;
			if(y==4) return STRING_JA;
			return null;
		}
	}
	
	
	
	
	
	
	private class TableCellRenderer0 extends JLabel implements TableCellRenderer
	{
		private Font defaultFont;
		public TableCellRenderer0()
		{
			super();
			setOpaque(true);
			defaultFont = getFont();
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Font f = (Font) table.getModel().getValueAt(row,0);
			if(column>2) setFont(f);
			else setFont(defaultFont);
			
			setText(value.toString());
			return this;
		}
	}
	
	
	
	private class TableCellRendererFont extends JLabel implements TableCellRenderer
	{
		private Font defaultFont;
		public TableCellRendererFont()
		{
			Font f = getFont().deriveFont(Font.PLAIN);
			defaultFont = new Font(f.getName(),f.getStyle(),f.getSize());
		}
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Font f = (Font) value;
			setText(f.getFamily());
			return this;
		}
	}
}
