package a.entity.gus06.data.string.replace1.first;

import a.framework.*;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170319";}


	private Service quote;
	
	public EntityImpl() throws Exception
	{
		quote = Outside.service(this,"gus06.string.transform.regexp.quote");
	}



	public Object t(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String line = o[0];
		String s1 = o[1];
		String s2 = o[2];
		
		return replace(line,s1,s2);
	}
	
	private String replace(String line, String s1, String s2) throws Exception
	{
		return line.replaceFirst((String) quote.t(s1),Matcher.quoteReplacement(s2));
	}
}
