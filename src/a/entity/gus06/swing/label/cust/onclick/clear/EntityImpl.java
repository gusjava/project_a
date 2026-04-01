package a.entity.gus06.swing.label.cust.onclick.clear;

import a.framework.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140730";}

	
	
	public void p(Object obj) throws Exception
	{new Holder((JLabel) obj);}
	
	
	
	private class Holder extends MouseAdapter
	{
		private JLabel label;
		public Holder(JLabel label)
		{
			this.label = label;
			label.setFocusable(true);
			label.addMouseListener(this);
		}
		public void mousePressed(MouseEvent e)
		{resetLabel();}
    	
		private void resetLabel()
		{
			label.setText(" ");
			label.setIcon(null);
			label.setToolTipText(null);
		}
	}
}
