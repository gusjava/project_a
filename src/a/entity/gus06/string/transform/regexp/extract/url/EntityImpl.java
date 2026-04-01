package a.entity.gus06.string.transform.regexp.extract.url;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}
	
	public static final String REGEX = "https?:\\/\\/[a-z0-9._%+-]+\\.[a-z]{2,4}";


	private Pattern p;

	public EntityImpl() throws Exception
	{
		p = Pattern.compile(REGEX,Pattern.CASE_INSENSITIVE);
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
