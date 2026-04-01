package a.entity.gus06.convert.stringtopoint2d;

import a.framework.*;
import java.awt.geom.Point2D;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250826";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		if(s==null) return null;
		if(s.equals("")) return null;
		
		String[] n = s.split(" ");
		return new Point2D.Double(d_(n[0]),d_(n[1]));
	}
	
	private double d_(String s)
	{return Double.parseDouble(s);}
}
