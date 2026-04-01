package a.entity.gus06.appli.quartogame.gui.board;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Composite;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.util.HashMap;


public class EntityImpl extends S1 implements Entity, I, P, G {

	public String creationDate() {return "20191115";}
	
	public static final int GAP = 10;
	public static final int SIZE = 32;
	
	public static final String STATE_SELECT = "select";
	public static final String STATE_PUT = "put";
	public static final String STATE_OVER = "over";
	
	public static final Color COLOR_SELECTED = Color.CYAN;
	public static final Color COLOR_ACTIVE = new Color(245,245,245);
	

	private Service findIcons;
	private Service buildBorder;
	
	private Icon[] icons;

	private int[] data;
	private int[] left;
	
	private JPanel panel;
	private JTable table1;
	private JTable table2;
	
	private TableModel1 model1;
	private TableModel2 model2;
	
	private Object player;
	




	public EntityImpl() throws Exception
	{
		findIcons = Outside.service(this,"gus06.appli.quartogame.tool.iconarray");
		buildBorder = Outside.service(this,"gus06.swing.border.build.ovalborder");
		icons = (Icon[]) findIcons.g();
		
		model1 = new TableModel1();
		model2 = new TableModel2();
		
		table1 = new JTable(model1);
		table1.setCellSelectionEnabled(true);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setDefaultRenderer(Integer.class,new TableCellRenderer1());
		table1.setShowGrid(false);
		table1.setRowHeight(SIZE);
		resizeColumns(table1,SIZE);
		
		table2 = new JTable(model2);
		table2.setCellSelectionEnabled(true);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.setDefaultRenderer(Integer.class,new TableCellRenderer2());
		table2.setShowGrid(false);
		table2.setRowHeight(SIZE);
		resizeColumns(table2,SIZE);
		
		table1.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{table1Clicked(e);}
		});
		
		table2.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e)
			{table2Clicked(e);}
		});

		JPanel panel1 = new JPanel();
		panel1.add(table1);
		
		JPanel panel2 = new JPanel();
		panel2.add(table2);
		
		panel = new JPanel(new BorderLayout());
		panel.add(panel2,BorderLayout.NORTH);
		panel.add(panel1,BorderLayout.CENTER);
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		data = (int[]) get1(map,"data");
		player = get1(map,"player");
		
		if(data.length!=17) throw new Exception("Invalid data length: "+data.length);
		
		left = new int[16];
		for(int i=0;i<16;i++) left[i] = i+1;
		for(int i=0;i<16;i++) if(data[i]!=0) left[data[i]-1] = 0;
		
		model1.fireTableDataChanged();
		model2.fireTableDataChanged();
	}
	
	
	
	
	public Object g() throws Exception
	{return data;}
	
	
	
	
	private int getSelected()
	{return data[16];}
	
	private void setSelected(int v)
	{data[16] = v;}
	
	private void clearSelected()
	{setSelected(0);}
	
	
	
	private int getAt(int x, int y)
	{return data[x*4+y];}
	
	private void setAt(int x, int y, int v)
	{data[x*4+y] = v;}
	
	private int leftAt(int x, int y)
	{return left[x*8+y];}
	




	private class TableModel1 extends AbstractTableModel
	{
		public int getColumnCount() {return 4;}
		public int getRowCount() {return 4;}
		public boolean isCellEditable(int x, int y){return false;}
		public Class getColumnClass(int y){return Integer.class;}

		public Object getValueAt(int x, int y)
		{
			if(data==null) return null;
			return Integer.valueOf(data[x*4+y]);
		}
	}
	
	private class TableModel2 extends AbstractTableModel
	{
		public int getColumnCount() {return 8;}
		public int getRowCount() {return 2;}
		public boolean isCellEditable(int x, int y){return false;}
		public Class getColumnClass(int y){return Integer.class;}

		public Object getValueAt(int x, int y)
		{
			if(left==null) return null;
			return Integer.valueOf(left[x*8+y]);
		}
	}
	
	
	
	
	
	
	
	public void table1Clicked(MouseEvent e)
	{
		int selected = getSelected();
		if(selected==0) return;
		
		int x = table1.getSelectedRow();
		int y = table1.getSelectedColumn();
		if(getAt(x,y)>0) return;
		
		setAt(x,y,selected);
		left[selected-1] = 0;
		clearSelected();
		
		model1.fireTableDataChanged();
		model2.fireTableDataChanged();
		actionPerformed();
	}
	
	public void table2Clicked(MouseEvent e)
	{
		int selected = getSelected();
		if(selected>0) return;
		
		int x = table2.getSelectedRow();
		int y = table2.getSelectedColumn();
		int value = leftAt(x,y);
		if(value==0) return;
		
		setSelected(value);
		
		model1.fireTableDataChanged();
		model2.fireTableDataChanged();
		actionPerformed();
	}
	
	
	
	
	private Object get0(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return map.get(key);
	}
	
	
	
	
	private void resizeColumns(JTable table, int length)
	{
		for(int i=0;i<table.getColumnCount();i++)
		resizeColumns(table,i,length);
	}
	
	private void resizeColumns(JTable table, int column, int length)
	{
		table.getColumnModel().getColumn(column).setMinWidth(length);
		table.getColumnModel().getColumn(column).setMaxWidth(length);
	}
	
	
	
	
	private class TableCellRenderer1 extends JLabel implements TableCellRenderer
	{
		private Icon icon;
		
		public TableCellRenderer1()
		{
			setOpaque(true);
			setBorder(buildBorder(Color.GRAY));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Integer v = (Integer) value;
			int vv = v==null?0:v.intValue();
			icon = vv>0 ? icons[vv-1] : null;
			
			boolean active = vv==0 && getSelected()!=0;
			
			setBackground(active ? COLOR_ACTIVE : Color.WHITE);
			return this;
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(icon!=null) paintIcon((Graphics2D) g);
		}
		
		private void paintIcon(Graphics2D g)
		{
			int x = (getWidth()-icon.getIconWidth())/2;
			int y = (getHeight()-icon.getIconHeight())/2;
			icon.paintIcon(this,g,x,y-3);
		}
	}
	
	
	
	
	private class TableCellRenderer2 extends JLabel implements TableCellRenderer
	{
		private Icon icon;
		
		public TableCellRenderer2()
		{
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Integer v = (Integer) value;
			int vv = v==null?0:v.intValue();
			icon = vv>0 ? icons[vv-1] : null;
			
			boolean selected = vv>0 && vv==getSelected();
			boolean active = vv>0 && getSelected()==0;
			
			setBackground(active ? COLOR_ACTIVE : Color.WHITE);
			setBorder(buildBorder(selected ? COLOR_SELECTED : Color.GRAY));
			return this;
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(icon!=null) paintIcon((Graphics2D) g);
		}
		
		private void paintIcon(Graphics2D g)
		{
			int x = (getWidth()-icon.getIconWidth())/2;
			int y = (getHeight()-icon.getIconHeight())/2;
			icon.paintIcon(this,g,x,y-3);
		}
	}
	
	
	
	private Border buildBorder(Color color)
	{
		try
		{
			Border b1 = (Border) buildBorder.t(new Object[]{10,10,color});
			Border b2 = BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP);
			return BorderFactory.createCompoundBorder(b1,b2);
		}
		catch(Exception e)
		{Outside.err(this,"buildBorder(Color)",e);}
		return BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP);
	}
	
	
	private void actionPerformed()
	{send(this,"actionPerformed()");}
}
