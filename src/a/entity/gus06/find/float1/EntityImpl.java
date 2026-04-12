package a.entity.gus06.find.float1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}

	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof Float) return obj;
		if(obj instanceof Number) return numberToFloat((Number) obj);
		if(obj instanceof String) return stringToFloat((String) obj);
		
		if(obj instanceof float[])
		{
			float[] d = (float[]) obj;
			if(d.length!=1) throw new Exception("Invalid array length: "+d.length);
			return Float.valueOf(d[0]);
		}
		
		if(obj instanceof float[][])
		{
			float[][] d = (float[][]) obj;
			if(d.length!=1) throw new Exception("Invalid array length: "+d.length);
			if(d[0].length!=1) throw new Exception("Invalid array length: "+d[0].length);
			return Float.valueOf(d[0][0]);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Float numberToFloat(Number n)
	{
		float d = n.floatValue();
		return Float.valueOf(d);
	}
	
	private Float stringToFloat(String s)
	{
		s = s.trim();
		if(s.endsWith("%")) return percentToFloat(s);
		return Float.valueOf(s.replace(",","."));
	}
	
	private Float percentToFloat(String s)
	{
		s = s.substring(0,s.length()-1).trim();
		float d = Float.parseFloat(s.replace(",","."));
		return Float.valueOf(d/100.0f);
	}
}
