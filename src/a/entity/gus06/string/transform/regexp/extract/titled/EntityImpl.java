package a.entity.gus06.string.transform.regexp.extract.titled;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}
	
	private Service regex;
	private Pattern p;

	public EntityImpl() throws Exception
	{
		regex = Outside.service(this,"gus06.string.regex.titled");
		p = (Pattern) regex.g();
	}

	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Matcher m = p.matcher(text);
		
		StringBuffer b = new StringBuffer();
		while(m.find()) b.append(m.group()+"\n");
		return b.toString().trim();
	}
}
