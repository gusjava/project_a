package a.entity.gus06.data.string.replacer4.t;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220501";}


	private Service buildPattern;

	public EntityImpl() throws Exception
	{
		buildPattern = Outside.service(this,"gus06.string.extract.extract1.pattern");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = (String) o[0];
		String s1 = (String) o[1];
		T t = (T) o[2];
		
		String pattern = (String) buildPattern.t(s1);
		Pattern p = Pattern.compile(pattern,Pattern.DOTALL);
		
		StringBuffer b = new StringBuffer();
		Matcher m = p.matcher(line);
		
		while(m.find())
		{
			String repl1 = m.group(1);
			String repl2 = toString(t.t(repl1));
			String repl3 = s1.replace("***",repl2).replace("**",repl2).replace("*",repl2);
			
			m.appendReplacement(b,repl3);
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