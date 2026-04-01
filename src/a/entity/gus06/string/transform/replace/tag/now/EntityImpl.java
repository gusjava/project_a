package a.entity.gus06.string.transform.replace.tag.now;

import a.framework.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150527";}

	Pattern p = Pattern.compile("\\{([^\\}]+)\\}");
	
	
	public Object t(Object obj) throws Exception
	{
		String text = (String) obj;
		StringBuffer b = new StringBuffer();
		
		Matcher m = p.matcher(text);
		int end0 = 0;
		
		while(m.find())
		{
			String format = m.group(1);
			
			b.append(text.substring(end0,m.start()));
			b.append(compute(format));
			
			end0 = m.end();
		}
		
		b.append(text.substring(end0,text.length()));
		return b.toString();
	}
	
	
	
	private String compute(String format) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}
}
