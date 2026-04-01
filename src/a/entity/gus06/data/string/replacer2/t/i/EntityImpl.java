package a.entity.gus06.data.string.replacer2.t.i;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180215";}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = (String) o[0];
		String s1 = (String) o[1];
		T t = (T) o[2];
		
		StringBuffer b = new StringBuffer();
		
		Pattern p = Pattern.compile("(?i)"+s1);
		Matcher m = p.matcher(line);
		
		int group = m.groupCount()>0?1:0;
		
		while(m.find())
		{
			String g = m.group(group);
			String g1 = toString(t.t(g));
			m.appendReplacement(b,g1);
		}
		m.appendTail(b);
		return b.toString();
	}
	
	
	private String toString(Object obj) throws Exception
	{
		if(obj==null) return "null";
		if(obj instanceof String) return ""+obj;
		if(obj instanceof Number) return ""+obj;
		if(obj instanceof Boolean) return ""+obj;
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
