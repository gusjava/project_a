package a.entity.gus06.string.extract.html.data1;

import a.framework.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170328";}

	private Service decode;
	private Service rmScripts;
	private Service whitespace;
	
	private Pattern p;


	public EntityImpl() throws Exception
	{
		decode = Outside.service(this,"gus06.string.transform.format.html.decode");
		rmScripts = Outside.service(this,"gus06.string.transform.format.html.rm.scripts");
		whitespace = Outside.service(this,"gus06.string.transform.normalize.whitespace");
		
		p = Pattern.compile("(?s)<[^>]+>");
	}
	



	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		s = (String) rmScripts.t(s);
		
		Matcher m = p.matcher(s);
		
		List list = new ArrayList();
		int k = 0;
		while(m.find())
		{
			int start = m.start();
			int end = m.end();
			
			String tag = m.group();
			String part = s.substring(k,start);
			
			list.add(decode(part));
			list.add(whitespace(tag));
			k = end;
		}
		
		String part = s.substring(k,s.length());
		list.add(decode(part));
		
		return list;
	}
	
	
	private String decode(String s) throws Exception
	{return (String) decode.t(s);}
	
	private String whitespace(String s) throws Exception
	{return (String) whitespace.t(s);}
}
