package a.entity.gus06.appli.chessgame.gui.board;

import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import java.util.Map;


public class EntityImpl extends S1 implements Entity, I, P, G, MouseListener, MouseMotionListener {

	public String creationDate() {return "20150409";}
	
	public static final int NB = 8;
	
	public static final String PLAYER_WHITE = "white";
	public static final String PLAYER_BLACK = "black";
	
	public static final String STATE_CHECK = "check";
	public static final String STATE_MATE = "mate";
	
	public static final Color COLOR_CHECK = Color.ORANGE;
	public static final Color COLOR_MATE = Color.RED;
	
	public static final Composite ALPHA = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.4f);
	
	

	private Service findIcon;
	private Service findColor;


	private int[][] data;
	
	private JPanel panel;
	
	private JTable0 table;
	private BoardTableModel model;
	
	private Icon draggedIcon;
	private int mouse_x;
	private int mouse_y;
	
	private int[] start;
	private int[] end;
	
	private String player;
	private String state;




	public EntityImpl() throws Exception
	{
		findIcon = Outside.service(this,"gus06.appli.chessgame.tool.findicon");
		findColor = Outside.service(this,"gus06.appli.chessgame.tool.findcolor");
		
		model = new BoardTableModel();
		table = new JTable0(model);
		
		table.setCellSelectionEnabled(true);
		table.setDefaultRenderer(Integer.class,new ChessTableCellRenderer());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(false);
		table.setRowHeight(40);
		
		table.addMouseListener(this);
		table.addMouseMotionListener(this);

		panel = new JPanel();
		panel.add(table);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		Map map = (Map) obj;
		data = (int[][]) map.get("board");
		player = (String) map.get("player");
		state = (String) map.get("state");
		
		updateGui();
	}
	
	
	public Object g() throws Exception
	{
		if(start==null || end==null) return null;
		return new Object[]{start,end};
	}
	
	
	
	private void updateGui()
	{
		try
		{
			 model.fireTableDataChanged();
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}




	private class BoardTableModel extends AbstractTableModel
	{
		public int getColumnCount() {return NB;}
		public int getRowCount() {return NB;}
		public boolean isCellEditable(int x, int y){return false;}
		public Class getColumnClass(int y){return Integer.class;}

		public Object getValueAt(int x, int y)
		{
			if(data==null) return null;
			return Integer.valueOf(data[x][y]);
		}
	}
	
	
	
	
	private class ChessTableCellRenderer extends JLabel implements TableCellRenderer
	{
		private Icon icon;
		private boolean isTransparent;
		
		public ChessTableCellRenderer()
		{setOpaque(true);}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			Integer v = (Integer) value;
			int vv = v==null?0:v.intValue();
			
			isTransparent = isDragStart(row,column);
			icon = icon(v);
			
			Color color = color(row,column);
			if(isMyKing(vv))
			{
				if(state.equals(STATE_CHECK)) color = COLOR_CHECK;
				if(state.equals(STATE_MATE)) color = COLOR_MATE;
			}
			
			setBackground(color);
			return this;
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(icon!=null) paintIcon((Graphics2D) g);
		}
		
		private void paintIcon(Graphics2D g)
		{
			Composite composite = g.getComposite();
			if(isTransparent) g.setComposite(ALPHA);
			
			int x = (getWidth()-icon.getIconWidth())/2;
			int y = (getHeight()-icon.getIconHeight())/2;
			
			icon.paintIcon(this,g,x,y);
			g.setComposite(composite);
		}
	}
	
	
	
	
	private Icon icon(Integer v)
	{
		try{return (Icon) findIcon.t(v);}
		catch(Exception e){Outside.err(this,"icon(Integer)",e);}
		return null;
	}
	
	private Color color(int row, int column)
	{
		try{return (Color) findColor.t(new int[]{row,column});}
		catch(Exception e){Outside.err(this,"color(int,int)",e);}
		return null;
	}
	
	
	
	private boolean isDragStart(int row, int column)
	{
		if(start==null) return false;
		if(end!=null) return false;
		return start[0]==row && start[1]==column;
	}
	
	
	private boolean isMyKing(int vv)
	{
		if(player.equals(PLAYER_WHITE)) return vv==6;
		if(player.equals(PLAYER_BLACK)) return vv==-6;
		return false;
	}
	
	
	private Integer startValue()
	{
		if(data==null || start==null) return null;
		return Integer.valueOf(data[start[0]][start[1]]);
	}
	
	





	private class JTable0 extends JTable
	{
		public JTable0(AbstractTableModel model) {super(model);}
		public Dimension getPreferredSize()
		{
			Dimension d = super.getPreferredSize();
			return new Dimension(d.height,d.height);
		}
		
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(draggedIcon!=null) paintDraggedIcon((Graphics2D) g);
		}
		
		private void paintDraggedIcon(Graphics2D g)
		{
			int x = mouse_x - (draggedIcon.getIconWidth()/2);
			int y = mouse_y - (draggedIcon.getIconHeight()/2);
			draggedIcon.paintIcon(this,g,x,y);
		}
	}
	
	
	
	private void movePerformed()
	{send(this,"movePerformed()");}
	
	
	
	public void mouseClicked(MouseEvent e)
	{}
	
	public void mousePressed(MouseEvent e)
	{
		start = null;
		end = null;
		
		int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		
		if(data==null || data[x][y]==0) return;
		
		start = new int[]{x,y};
		mouse_x = e.getX();
		mouse_y = e.getY();
		draggedIcon = icon(startValue());
		table.repaint();
	}
	
	
	public void mouseReleased(MouseEvent e)
	{
		int x = table.getSelectedRow();
		int y = table.getSelectedColumn();
		
		if(data==null || start==null) return;
		
		end = new int[]{x,y};
		draggedIcon = null;
		table.repaint();
		movePerformed();
	}
	
	public void mouseEntered(MouseEvent e)
	{
		start = null;
		end = null;
		draggedIcon = null;
		table.repaint();
	}
	
	public void mouseExited(MouseEvent e)
	{
		start = null;
		end = null;
		draggedIcon = null;
		table.repaint();
	}
	
	public void mouseMoved(MouseEvent e)
	{
	}
	
	public void mouseDragged(MouseEvent e)
	{
		mouse_x = e.getX();
		mouse_y = e.getY();
		table.repaint();
	}
}
