package a.entity.gus06.swing.comp.cust.onrollover.bgwhite;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160501";}
	
	public static final Color COLOR = Color.WHITE;


	
	public void p(Object obj) throws Exception
	{new Holder((JComponent) obj);}


	
	private class Holder implements MouseListener
	{
		private JComponent comp;
		private Color background;
		private boolean isOpaque;
		
		public Holder(JComponent comp)
		{
			this.comp = comp;
			comp.setFocusable(true);
			comp.addMouseListener(this);
		}

		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}
		
		public void mouseEntered(MouseEvent e)
		{
			background = comp.getBackground();
			isOpaque = comp.isOpaque();
			
			comp.setBackground(COLOR);
			comp.setOpaque(true);
			comp.requestFocusInWindow();
		}
		public void mouseExited(MouseEvent e)
		{
			comp.setBackground(background);
			comp.setOpaque(isOpaque);
		}
	}
}
