package a.entity.gus06.swing.tabbedpane.build.draggable;

import a.framework.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141109";}
	
	
	public Object i() throws Exception
	{return new DraggableTabbedPane();}
	
	
	private void paintBackground(P bgPainter, JTabbedPane pane, Graphics g)
	{
		try{bgPainter.p(new Object[]{pane,g});}
		catch(Exception e)
		{Outside.err(this,"paintBackground(Give,JTabbedPane,Graphics)",e);}
	}
	
	
	
	private class DraggableTabbedPane extends JTabbedPane implements MouseListener, MouseMotionListener, S, V, R
	{	
		private S1 d = new S1();
		
		public void addActionListener(ActionListener listener) {d.addActionListener(listener);}
		public void removeActionListener(ActionListener listener) {d.removeActionListener(listener);}
		public List listeners() {return d.listeners();}
		
		private P bgPaint;
		private BufferedImage tabImage;
		private Point mouse;
		
		private int index0 = -1;
		private int index1 = -1;

		public DraggableTabbedPane()
		{
			super();
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		public void mouseMoved(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		
		public void mouseDragged(MouseEvent e)
		{
			int index = getUI().tabForCoordinate(this,e.getX(),e.getY());
			
			if(index0==-1) index0 = index;
			if(index0!=-1 && tabImage==null) tabImage = buildTabImage(index0);
			
			index1 = index;
			mouse = e.getPoint();
			repaint();
		}
		
		public void mouseReleased(MouseEvent e)
		{
			if(index0==-1) return;

			if(index1!=-1 && index1!=index0)
				invertTabs();

			index0 = -1;
			index1 = -1;
			tabImage = null;
			mouse = null;
			repaint();
		}
		
		private void invertTabs()
		{
			Component comp = getComponentAt(index0);
			Component tabComp = getTabComponentAt(index0);
			Icon icon = getIconAt(index0);
			String title = getTitleAt(index0);
			String tooltip = getToolTipTextAt(index0);
			
			if(tabComp!=null && tabComp instanceof JLabel1)
				tabComp = null;

			removeTabAt(index0);
			insertTab(title,icon,comp,tooltip,index1);
			setTabComponentAt(index1,tabComp);
			setSelectedIndex(index1);
			tabsInverted();
		}
		
		
		private BufferedImage buildTabImage(int index)
		{
			Component tabComp = getTabComponentAt(index);
			if(tabComp==null)
			{
				setTabComponentAt(index,new JLabel1(index));
				return null;
			}
			BufferedImage image = new BufferedImage(tabComp.getWidth(),tabComp.getHeight(),BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.getGraphics();
			tabComp.print(g);
			return image;
		}
		

		protected void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			if(bgPaint!=null) paintBackground(bgPaint,this,g);
			if(index0!=-1 && mouse!=null && tabImage!=null)
			g.drawImage(tabImage,mouse.x,mouse.y-tabImage.getHeight()/2,this);
		}
		
		
		private class JLabel1 extends JLabel
		{
			public JLabel1(int index)
			{
				super(getTitleAt(index));
				setIcon(getIconAt(index));
			}
		}
		
		
		private void tabsInverted()
		{d.send(this,"tabsInverted()");}
		
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("bgPaint"))
			{bgPaint = (P) obj;return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("index0")) return ""+index0;
			if(key.equals("index1")) return ""+index1;
			
			if(key.equals("keys")) return new String[]{"index0","index1"};
			throw new Exception("Unknown key: "+key);
		}
	}
}
