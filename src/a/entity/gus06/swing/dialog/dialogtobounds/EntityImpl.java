package a.entity.gus06.swing.dialog.dialogtobounds;

import a.framework.*;
import java.awt.Rectangle;
import javax.swing.JDialog;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160605";}

	
	public Object t(Object obj) throws Exception
	{
		JDialog dialog = (JDialog) obj;
		Rectangle rect = dialog.getBounds();
		
		int x = rect.x;
		int y = rect.y;
		int w = rect.width;
		int h = rect.height;
		
		return x+" "+y+" "+w+" "+h;
	}
}
