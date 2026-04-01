package a.entity.gus06.string.transform.regexp.extract.integer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160804";}
	
	public static final String REGEX = "[0-9]+";


	private Pattern p;

	public EntityImpl() throws Exception
	{
		p = Pattern.compile(REGEX,Pattern.DOTALL);
	}



	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Matcher m = p.matcher(text.toLowerCase());
		
		StringBuffer b = new StringBuffer();
		while(m.find()) b.append(m.group()+"\n");
		return b.toString().trim();
	}
}
