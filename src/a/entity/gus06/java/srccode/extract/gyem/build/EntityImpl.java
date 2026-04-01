package a.entity.gus06.java.srccode.extract.gyem.build;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140906";}

	private Pattern p = Pattern.compile("\"([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])\"",Pattern.MULTILINE);

	public EntityImpl() throws Exception
	{
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String src = (String) obj;
		Matcher m = p.matcher(src);
		if(!m.find()) throw new Exception("Gyem version date not found inside src code");
		return m.group(1);
	}
}
