package a.entity.gus06.math.tabdouble.point.ratoxy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180501";}

	
	
	public Object t(Object obj) throws Exception
	{
		double[] d = (double[]) obj;
		if(d.length!=2) throw new Exception("Invalid array length: "+d.length);
		
		double r = d[0];
		double a = d[1];
		
		double x = r*Math.cos(a);
		double y = r*Math.sin(a);
		
		return new double[]{x,y};
	}
}