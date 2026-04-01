package a.entity.gus06.find.double1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160705";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Double) return obj;
		if(obj instanceof Number) return numberToDouble((Number) obj);
		if(obj instanceof String) return stringToDouble((String) obj);
		
		if(obj instanceof double[])
		{
			double[] d = (double[]) obj;
			if(d.length!=1) throw new Exception("Invalid array length: "+d.length);
			return Double.valueOf(d[0]);
		}
		
		if(obj instanceof double[][])
		{
			double[][] d = (double[][]) obj;
			if(d.length!=1) throw new Exception("Invalid array length: "+d.length);
			if(d[0].length!=1) throw new Exception("Invalid array length: "+d[0].length);
			return Double.valueOf(d[0][0]);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Double numberToDouble(Number n)
	{
		double d = n.doubleValue();
		return Double.valueOf(d);
	}
	
	private Double stringToDouble(String s)
	{
		s = s.trim();
		if(s.endsWith("%")) return percentToDouble(s);
		return Double.valueOf(s.replace(",","."));
	}
	
	private Double percentToDouble(String s)
	{
		s = s.substring(0,s.length()-1).trim();
		double d = Double.parseDouble(s.replace(",","."));
		return Double.valueOf(d/100.0);
	}
}
