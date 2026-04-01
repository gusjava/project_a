package a.entity.gus06.data.perform.truncate.pretty;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221005";}

	public static final String END = "...";
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		int limit = toInt(o[1]);
		if(limit<0) throw new Exception("Invalid limit value: "+limit);
		
		if(input instanceof String)
		{
			String s = (String) input;
			return s.length()>limit ? s.substring(0,limit)+END : s;
		}
		if(input instanceof Number)
		{
			String s = ""+input;
			return s.length()>limit ? s.substring(0,limit)+END : s;
		}
		if(input instanceof Boolean)
		{
			String s = ""+input;
			return s.length()>limit ? s.substring(0,limit)+END : s;
		}
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}

	
	private int toInt(Object obj)
	{return Integer.parseInt(obj.toString());}
}