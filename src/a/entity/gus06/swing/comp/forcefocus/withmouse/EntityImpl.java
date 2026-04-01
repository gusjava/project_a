package a.entity.gus06.swing.comp.forcefocus.withmouse;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JComponent;
import javax.swing.JDialog;
import java.awt.Point;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}


	private Service forceJFrame;
	private Service forceJDialog;
	private Service forceClickAt;


	public EntityImpl() throws Exception
	{
		forceJFrame = Outside.service(this,"gus06.swing.frame.forcefocus.withmouse");
		forceJDialog = Outside.service(this,"gus06.swing.dialog.forcefocus.withmouse");
		forceClickAt = Outside.service(this,"gus06.mouse.forceclickat");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JComponent comp = (JComponent) obj;
		Object ancestor = SwingUtilities.getWindowAncestor(comp);
		
		if(ancestor instanceof JFrame)
		{
			JFrame frame = (JFrame) ancestor;
			if(!frame.isVisible()) return;
			
			if(!frame.isUndecorated())
			{
				forceJFrame.p(frame);
				comp.requestFocusInWindow();
				return;
			}
		}
			
		if(ancestor instanceof JDialog)
		{
			JDialog dialog = (JDialog) ancestor;
			if(!dialog.isVisible()) return;
			
			if(!dialog.isUndecorated())
			{
				forceJDialog.p(dialog);
				comp.requestFocusInWindow();
				return;
			}
		}
		
		Point p = comp.getLocationOnScreen();
		int[] p1 = new int[]{p.x+2,p.y+2};
		forceClickAt.p(p1);
	}
}
