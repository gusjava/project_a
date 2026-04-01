package a.entity.gus06.sys.textcomparator1.scrollpainter;

import a.framework.*;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.plaf.metal.MetalScrollBarUI;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.BoundedRangeModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20201201";}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		JScrollPane scroll = (JScrollPane) o[0];
		R holder = (R) o[1];
		
		ScrollBarUI1 ui = new ScrollBarUI1(holder);
		JScrollBar bar = scroll.getVerticalScrollBar();
        
		bar.addMouseListener(ui);
		bar.addMouseMotionListener(ui);
		bar.setBlockIncrement(0);
		bar.setUI(ui);
	}
	
	
	public class ScrollBarUI1 extends MetalScrollBarUI implements MouseListener, MouseMotionListener
	{
		private R holder;
		
		public ScrollBarUI1(R holder)
		{this.holder = holder;}
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {move(e);}
		public void mouseDragged(MouseEvent e) {move(e);}
		public void mouseMoved(MouseEvent e) {}
		
		
		private void move(MouseEvent e)
		{
			JScrollBar bar = (JScrollBar) e.getSource();
			BoundedRangeModel model = bar.getModel();
	        
			int decr_h = getDecrButton().getHeight();
			int incr_h = getIncrButton().getHeight();
			int h0 = bar.getHeight() - decr_h - incr_h;
	        
			double v = (e.getPoint().getY() - incr_h)/(double)h0;
	        
			int min = model.getMinimum();
			int max = model.getMaximum();
	        
			int newValue = (int)(min + (max-min)*v);
			model.setValue(newValue);
		}
		
		
		
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds)
		{
			super.paintTrack(g,c,trackBounds);
			paintHighlight(g,trackBounds);
		}
    
		protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds)
		{
			super.paintThumb(g,c,thumbBounds);
			paintHighlight(g,getTrackBounds());
        
			g.setColor(Color.BLACK);
			g.drawRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,thumbBounds.height);
		}

    
		protected void paintHighlight(Graphics g, Rectangle trackBounds)
		{
			int w = trackBounds.width;
			int h = trackBounds.height;
        
			int x0 = trackBounds.x;
			int y0 = trackBounds.y;
			
			List list1 = (List) retrieve(holder,"list1");
			Integer lineNb = (Integer) retrieve(holder,"lineNb");
			double step = (double)h/(double)lineNb;
        
			for(int i=0;i<list1.size();i++)
			{
				Object[] info = (Object[]) list1.get(i);
				Integer index = (Integer) info[0];
				Color color = (Color) info[2];
            
				int y = (int)(y0 + index*step);
				int dy = (int) step;
				if(dy==0)dy=1;
            
				g.setColor(color);
				g.fillRect(x0,y,w,dy+1);
			}
		}
    
		protected Dimension getMinimumThumbSize()
		{return new Dimension(3,3);}
    
		public JButton getDecrButton()
		{return decrButton;}
    
		public JButton getIncrButton()
		{return incrButton;}
	}
	
	
	
	private Object retrieve(R r, String key)
	{
		try{return r.r(key);}
		catch(Exception e)
		{Outside.err(this,"retrieve(R,String)",e);}
		return null;
	}
}