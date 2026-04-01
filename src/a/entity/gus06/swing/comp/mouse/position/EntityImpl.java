package a.entity.gus06.swing.comp.mouse.position;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170820";}



	public Object t(Object obj) throws Exception
	{
		Component comp = (Component) obj;
		if(!comp.isShowing()) return null;
		
		Point p0 = comp.getLocationOnScreen();
		Point p = cursorLocation();
		
		int dx = p.x-p0.x;
		int dy = p.y-p0.y;
		
		return new int[]{dx,dy};
	}
	

	private Point cursorLocation()
	{
		PointerInfo info = MouseInfo.getPointerInfo();
		if(info==null) return null;
		return info.getLocation();
	}
}
