package a.entity.gus06.swing.scroll.autoposition1;

import a.framework.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.MouseEvent;
import javax.swing.BoundedRangeModel;
import javax.swing.plaf.metal.MetalScrollBarUI;
import javax.swing.JButton;


public class EntityImpl implements Entity, P, MouseListener, MouseMotionListener {

	public String creationDate() {return "20251201";}

	public void p(Object obj) throws Exception
	{
		JScrollBar bar = toBar(obj);
		bar.setUI(new HighScrollBarUI2());
		bar.addMouseListener(this);
		bar.addMouseMotionListener(this);
	}
	
	private JScrollBar toBar(Object obj) throws Exception
	{
		if(obj instanceof JScrollBar) return (JScrollBar) obj;
		if(obj instanceof JScrollPane) return ((JScrollPane) obj).getVerticalScrollBar();
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}

	public void mousePressed(MouseEvent e)  { move(e); }
	public void mouseDragged(MouseEvent e) { move(e); }

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}

	private void move(MouseEvent e)
	{
		JScrollBar bar = (JScrollBar) e.getSource();
		BoundedRangeModel model = bar.getModel();
		HighScrollBarUI2 ui = (HighScrollBarUI2) bar.getUI();

		int decr_h = ui.getDecrButton().getHeight();
		int incr_h = ui.getIncrButton().getHeight();
		int h0 = bar.getHeight() - decr_h - incr_h;

		double v = (e.getPoint().getY() - incr_h) / (double) h0;

		int min = model.getMinimum();
		int max = model.getMaximum();

		int newValue = (int) (min + (max - min) * v);
		bar.getModel().setValue(newValue);
	}
	
	public class HighScrollBarUI2 extends MetalScrollBarUI
	{
		public JButton getDecrButton() {return decrButton;}
		public JButton getIncrButton() {return incrButton;}
	}

}
