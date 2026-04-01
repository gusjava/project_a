package a.entity.gus06.awt.window.filter.mouseinside;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Window;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20161006";}



	public boolean f(Object obj) throws Exception
	{
		Window window = (Window) obj;
		if(!window.isVisible()) return false;
		
		Point p = cursorLocation();
		if(p==null) return false;
		
		Rectangle bounds1 = window.getBounds();
		return bounds1.contains(p);
	}
	

	private Point cursorLocation()
	{
		PointerInfo info = MouseInfo.getPointerInfo();
		if(info==null) return null;
		return info.getLocation();
	}
}
