package a.entity.gus06.swing.label.cust.tooltip1;

import a.framework.*;
import javax.swing.JLabel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250717";}

	
	public void p(Object obj) throws Exception
	{
		JLabel label = (JLabel) obj;
		new TooltipHandler(label);
	}
	
	
	private class TooltipHandler extends MouseMotionAdapter
	{
		private JLabel label;
		public TooltipHandler(JLabel label)
		{
			super();
			this.label = label;
			label.addMouseMotionListener(this);
		}
		
		public void mouseMoved(MouseEvent evt)
		{
			label.setToolTipText(label.getText());
		}
	}
}