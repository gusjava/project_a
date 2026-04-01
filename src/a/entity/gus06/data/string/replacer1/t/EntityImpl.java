package a.entity.gus06.data.string.replacer1.t;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161028";}


	private Service quote;

	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = (String) o[0];
		String s1 = (String) o[1];
		T t = (T) o[2];
		
		StringBuffer b = new StringBuffer();
		
		Pattern p = Pattern.compile((String) quote.t(s1));
		Matcher m = p.matcher(line);
		
		while(m.find())
		{
			String g = m.group();
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
