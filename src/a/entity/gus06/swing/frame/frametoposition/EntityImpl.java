package a.entity.gus06.swing.frame.frametoposition;

import a.framework.*;
import java.awt.Rectangle;
import javax.swing.JFrame;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180403";}

	
	public Object t(Object obj) throws Exception
	{
		JFrame frame = (JFrame) obj;
		Rectangle rect = frame.getBounds();
		
		int x = rect.x;
		int y = rect.y;
		
		return x+" "+y;
	}
}
