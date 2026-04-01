package a.entity.gus06.swing.comp.cust.focusonclicked;

import a.framework.*;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180308";}


	public void p(Object obj) throws Exception
	{new Holder((Component) obj);}
	
	
	
	public class Holder extends MouseAdapter
	{
		private Component comp;
	
		public Holder(Component comp)
		{
			this.comp = comp;
			comp.setFocusable(true);
			comp.addMouseListener(this);
		}
	
		public void mousePressed(MouseEvent e)
		{
			comp.requestFocusInWindow();
		}
	}
}
