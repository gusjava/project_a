package a.entity.gus.x.swing.comp.cust.dragframe;

import a.framework.*;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20140913";}

	public void p(Object obj) throws Exception
	{new DragHolder((Component) obj);}
	
	public class DragHolder implements MouseMotionListener
	{
		private Component comp;
		private Point p0;
	
		public DragHolder(Component comp)
		{
			this.comp = comp;
			comp.addMouseMotionListener(this);
		}
	
		public void mouseMoved(MouseEvent e) {p0=null;}

		public void mouseDragged(MouseEvent e)
		{
			comp.requestFocusInWindow();
			Point p = e.getLocationOnScreen();
			if(p0!=null) moveComp(p0,p);
			p0 = p;
		}

		private void moveComp(Point p0, Point p)
		{
			int dx = p.x-p0.x;
			int dy = p.y-p0.y;
		
			Component frame = SwingUtilities.getRoot(comp);
			if(frame==null) return;
		
			Point q = frame.getLocation();
			frame.setLocation(q.x+dx,q.y+dy);
		}
	}
}