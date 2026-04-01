package a.entity.gus06.data.perform.splitreg2.last;

import a.framework.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160612";}

	
	
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
		Pattern p = Pattern.compile(regex,Pattern.DOTALL);
		Matcher m = p.matcher(s);
		
		int start = -1;
		int end = -1;
		
		while(m.find())
		{
			start = m.start();
			end = m.end();
		}
		
		if(start==-1 || end==-1) return null;
		
		String part1 = s.substring(0,start);
		String part2 = s.substring(end,s.length());
		
		return new String[]{part1,part2};
	}
}
