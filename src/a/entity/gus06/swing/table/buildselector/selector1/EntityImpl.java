package a.entity.gus06.swing.table.buildselector.selector1;

import a.framework.*;
import javax.swing.JTable;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250303";}


	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		return new Selector((JTable) obj);
	}
	
	
	private class Selector extends S1 implements KeyListener, MouseListener, MouseMotionListener, FocusListener, G
	{
		private JTable table;
		private Set selection;
		
		private int x0 = -1;
		private int y0 = -1;
		
		private int x1 = -1;
		private int y1 = -1;
		
		private boolean s0 = false;
		private Set selection0 = new HashSet();
		
		private boolean isAlting = false;
		
		
		public Selector(JTable table)
		{
			this.table = table;
			selection = new HashSet();
			
			table.addKeyListener(this);
			table.addMouseListener(this);
			table.addMouseMotionListener(this);
			table.addFocusListener(this);
		}
		
		public Object g() throws Exception
		{return selection;}
		
		// KEY LISTENER

		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_ALT) isAlting = true;
		}
		
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_ALT) isAlting = false;
		}
		
		public void keyTyped(KeyEvent e) {}
		
		// MOUSE LISTENER
		
		public void mousePressed(MouseEvent e)
		{
			Point p = e.getPoint();
			
			int x = table.rowAtPoint(p);
			int y = table.columnAtPoint(p);
			String key = x+"-"+y;
			
			s0 = selection.contains(key);
			x0 = x;
			y0 = y;
			x1 = x;
			y1 = y;
			
			if(isAlting) selectAlt(key);
			else select(key);
			
			selection0.clear();
			selection0.addAll(selection);
		}
		
		public void mouseReleased(MouseEvent e)
		{
			x0 = -1;
			y0 = -1;
			x1 = -1;
			y1 = -1;
			
			s0 = false;
			selection0.clear();
		}
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		
		// MOUSE MOTION LISTENER
		
		public void mouseDragged(MouseEvent e)
		{
			Point p = e.getPoint();
			
			int x = table.rowAtPoint(p);
			int y = table.columnAtPoint(p);
			
			if(x!=x1 || y!=y1)
			{
				List area = buildArea(x0,y0,x,y);
				selectArea(area);
				
				x1 = x;
				y1 = y;
			}
		}
		
		public void mouseMoved(MouseEvent e) {}
		
		// FOCUS LISTENER
		
		public void focusGained(FocusEvent e) {isAlting = false;}
		public void focusLost(FocusEvent e) {isAlting = false;}
		
		
		private void select(String key)
		{
			boolean selected = selection.contains(key);
			selection.clear();
			if(!selected) selection.add(key);
			selectionChanged();
		}
		
		private void selectAlt(String key)
		{
			boolean selected = selection.contains(key);
			if(!selected) selection.add(key);
			else selection.remove(key);
			selectionChanged();
		}
		
		private void selectArea(List area)
		{
			selection.clear();
			selection.addAll(selection0);
			
			if(s0) selection.removeAll(area);
			else selection.addAll(area);
			selectionChanged();
		}
		
		private List buildArea(int i1, int j1, int i2, int j2)
		{
			int i_min = Math.min(i1,i2);
			int i_max = Math.max(i1,i2);
			
			int j_min = Math.min(j1,j2);
			int j_max = Math.max(j1,j2);
		
			List area = new ArrayList();
			for(int i=i_min;i<=i_max;i++)
			for(int j=j_min;j<=j_max;j++)
			area.add(i+"-"+j);
			
			return area;
		}
		
		private void selectionChanged()
		{send(this,"selectionChanged()");}
	}
}
