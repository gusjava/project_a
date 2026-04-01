package a.entity.gus06.data.filter.swing.visible;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160916";}

	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj instanceof JFrame) return ((JFrame) obj).isVisible();
		if(obj instanceof JDialog) return ((JDialog) obj).isVisible();
		if(obj instanceof JComponent) return isVisible((JComponent) obj);
		
		return false;
	}
	
	
	private boolean isVisible(JComponent comp)
	{
		Object ancestor = SwingUtilities.getWindowAncestor(comp);
		
		if(ancestor instanceof JFrame) return ((JFrame) ancestor).isVisible();
		if(ancestor instanceof JDialog) return ((JDialog) ancestor).isVisible();
		return false;
	}
}
