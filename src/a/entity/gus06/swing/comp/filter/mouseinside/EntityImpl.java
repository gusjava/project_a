package a.entity.gus06.swing.comp.filter.mouseinside;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20160506";}



	public boolean f(Object obj) throws Exception
	{
		Component comp = (Component) obj;
		Rectangle frame = comp.getBounds();
		
		Point p = cursorLocation();
		if(p==null) return false;
		
		if(!comp.isShowing()) return false;
		frame.setLocation(comp.getLocationOnScreen());
		return frame.contains(p);
	}
	

	private Point cursorLocation()
	{
		PointerInfo info = MouseInfo.getPointerInfo();
		if(info==null) return null;
		return info.getLocation();
	}
}
