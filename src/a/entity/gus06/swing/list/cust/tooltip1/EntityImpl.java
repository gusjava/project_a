package a.entity.gus06.swing.list.cust.tooltip1;

import a.framework.*;
import javax.swing.JList;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20191218";}

	
	public void p(Object obj) throws Exception
	{
		JList list = (JList) obj;
		new TooltipHandler(list);
	}
	
	private class TooltipHandler extends MouseMotionAdapter
	{
		private JList list;
		public TooltipHandler(JList list)
		{
			super();
			this.list = list;
			list.addMouseMotionListener(this);
		}
		
		public void mouseMoved(MouseEvent evt)
		{
			Point p = evt.getPoint();
			int index = list.locationToIndex(p);
			if(index<0) return;
			
			Object targetElement = list.getModel().getElementAt(index);
			
			if(targetElement!=null)
			list.setToolTipText(targetElement.toString());
			else list.setToolTipText(null);
		}
	}
}
