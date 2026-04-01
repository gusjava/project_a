package a.entity.gus06.swing.dialog.forcefocus.withmouse;

import a.framework.*;
import java.awt.Point;
import javax.swing.JDialog;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160916";}


	private Service forceClickAt;

	public EntityImpl() throws Exception
	{
		forceClickAt = Outside.service(this,"gus06.mouse.forceclickat");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JDialog dialog = (JDialog) obj;
		if(!dialog.isVisible()) return;
		
		Point p = dialog.getLocationOnScreen();
		int[] p1 = new int[]{p.x+5,p.y+5};
		
		forceClickAt.p(p1);
	}
}
