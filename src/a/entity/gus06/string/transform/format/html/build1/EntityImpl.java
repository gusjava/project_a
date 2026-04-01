package a.entity.gus06.string.transform.format.html.build1;

import a.framework.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170208";}
	
	public static final Pattern P = Pattern.compile("https?:\\/\\/[a-zA-Z0-9._%+-=?&\\/]+");


	private Service encode;


	public EntityImpl() throws Exception
	{
		encode = Outside.service(this,"gus06.string.transform.format.html.encode");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		Matcher m = P.matcher(s);
		
		StringBuffer b = new StringBuffer();
		int offset = 0;
		
		while(m.find())
		{
			int start = m.start();
			int end = m.end();
			String url = m.group();
			
			b.append(encode(s,offset,start));
			b.append(urlToTag(url));
			
			offset = end;
		}
		if(offset<s.length())
		b.append(encode(s,offset,s.length()));
		
		return b.toString();
	}
	
	
	
	private String encode(String s, int start, int end) throws Exception
	{return (String) encode.t(s.substring(start,end));}
	
	private String urlToTag(String url)
	{return "<a target=\"_blank\" href=\""+url+"\">"+url+"</a>";}
}
