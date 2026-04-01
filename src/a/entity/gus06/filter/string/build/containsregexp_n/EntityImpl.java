package a.entity.gus06.filter.string.build.containsregexp_n;

import java.util.regex.Pattern;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}
	
	
	private Service normalize;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	private String normalize(String s) throws Exception
	{return (String) normalize.t(s);}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return new Filter(s);
	}
	
	
	
	
	private class Filter implements F
	{
		private Pattern p;
		public Filter(String regexp)
		{p = Pattern.compile(regexp, Pattern.DOTALL);}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = normalize((String) toString.t(obj));
			return p.matcher(str).find();
		}
	}
}
