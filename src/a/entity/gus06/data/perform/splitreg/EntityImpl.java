package a.entity.gus06.data.perform.splitreg;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160204";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object input = o[0];
		String regex = (String) o[1];
		
		if(input instanceof String)
		return split((String) input,regex);
		
		throw new Exception("Invalid data type: "+input.getClass().getName());
	}
	
	
	private String[] split(String s, String regex)
	{
		return s.split(regex,-1);
	}
}
