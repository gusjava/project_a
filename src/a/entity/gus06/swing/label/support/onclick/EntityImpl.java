package a.entity.gus06.swing.label.support.onclick;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150609";}


	
	public Object t(Object obj) throws Exception
	{return new Holder((JLabel) obj);}
	
	
	
	private class Holder extends S1 implements MouseListener
	{
		public Holder(JLabel label)
		{
			label.setFocusable(true);
			label.addMouseListener(this);
		}
		
		public void mousePressed(MouseEvent e) {perform();}
		public void mouseReleased(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
    	
		private void perform()
		{send(this,"perform()");}
	}
}
