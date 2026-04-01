package a.entity.gus06.math.tabint.point.xytora;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180501";}

	
	
	public Object t(Object obj) throws Exception
	{
		int[] d = (int[]) obj;
		if(d.length!=2) throw new Exception("Invalid array length: "+d.length);
		
		double x = (double) d[0];
		double y = (double) d[1];
		
		double r = Math.sqrt(x*x+y*y);
		double a = computeAngle(x,y);
		
		return new double[]{r,a};
	}
	
	
	private double computeAngle(double x, double y)
	{
		if(x==0)
		{
			if(y==0) return 0;
			if(y<0) return Math.PI*1.5;
			return Math.PI*0.5;
		}
		if(x>0)
		{
			if(y>=0) return Math.atan(y/x);
			return Math.atan(y/x)+Math.PI*2;
		}
		return Math.atan(y/x)+Math.PI;
	}
}