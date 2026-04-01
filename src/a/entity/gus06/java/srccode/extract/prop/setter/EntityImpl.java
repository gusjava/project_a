package a.entity.gus06.java.srccode.extract.prop.setter;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220928";}

	private Pattern p = Pattern.compile("set([A-Z][a-zA-Z0-9_]*)");
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		Matcher m = p.matcher(s);
		if(m.find()) return format(m.group(1));
			
		return null;
	}
	
	private String format(String s)
	{return s.substring(0, 1).toLowerCase() + s.substring(1);}
}