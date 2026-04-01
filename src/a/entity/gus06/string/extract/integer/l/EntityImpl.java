package a.entity.gus06.string.extract.integer.l;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170111";}
	
	public static final String REGEX = "[0-9]+";


	private Pattern p;

	public EntityImpl() throws Exception
	{
		p = Pattern.compile(REGEX);
	}



	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		Matcher m = p.matcher(text);
		
		Integer n = null;
		while(m.find())
		{
			String s = m.group();
			n = Integer.valueOf(s);
		}
		return n;
	}
}
