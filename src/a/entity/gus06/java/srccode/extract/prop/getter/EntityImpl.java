package a.entity.gus06.java.srccode.extract.prop.getter;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220928";}

	private Pattern p1 = Pattern.compile("get([A-Z][a-zA-Z0-9_]*)");
	private Pattern p2 = Pattern.compile("is([A-Z][a-zA-Z0-9_]*)");
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		Matcher m1 = p1.matcher(s);
		if(m1.find()) return format(m1.group(1));
		
		Matcher m2 = p2.matcher(s);
		if(m2.find()) return format(m2.group(1));
			
		return null;
	}
	
	private String format(String s)
	{return s.substring(0, 1).toLowerCase() + s.substring(1);}
}