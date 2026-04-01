package a.entity.gus06.find.boolean1;

import a.framework.*;

public class EntityImpl implements Entity, T, F {

	public String creationDate() {return "20160414";}
	
	
	
	public boolean f(Object obj) throws Exception
	{
		if(obj==null) throw new Exception("Invalid null value");
		
		if(obj instanceof Boolean) return ((Boolean) obj).booleanValue();
		
		if(obj instanceof Integer)
		{
			int v = ((Integer) obj).intValue();
			if(v==1) return true;
			if(v==0) return false;
			throw new Exception("Invalid integer value for boolean conversion: "+v);
		}
		
		if(obj instanceof Long)
		{
			int v = ((Long) obj).intValue();
			if(v==1) return true;
			if(v==0) return false;
			throw new Exception("Invalid long value for boolean conversion: "+v);
		}
		
		if(obj instanceof String)
		{
			String s = ((String) obj).toLowerCase();
			if(s.equals("true")) return true;
			if(s.equals("false")) return false;
			if(s.equals("1")) return true;
			if(s.equals("0")) return false;
			throw new Exception("Invalid string value for boolean conversion: "+s);
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return Boolean.valueOf(f(obj));
	}
}