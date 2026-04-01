package a.entity.gus06.string.transform.extract_a.integer.change.zero;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200308";}
	
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
		
		StringBuffer b = new StringBuffer();
		int offset = 0;
		
		while(m.find())
		{
			String s = m.group();
			int start = m.start();
			int end = m.end();
			
			int n = Integer.parseInt(s);
			int n1 = 0;
			
			String before = text.substring(offset,start);
			b.append(before);
			b.append(n1);
			
			offset = end;
		}
		
		String before = text.substring(offset,text.length());
		b.append(before);
		return b.toString();
	}
}
