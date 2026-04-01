package a.entity.gus06.filter.string.build.mstars2;

import a.framework.*;
import java.util.regex.Pattern;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161202";}


	private Service buildRegex;
	private Service toString;

	public EntityImpl() throws Exception
	{
		buildRegex = Outside.service(this,"gus06.filter.string.build.mstars2.regex");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		return new Filter((String) obj);
	}
	
	
	private class Filter implements F
	{
		private Pattern p;
		public Filter(String s) throws Exception
		{
			String regexp = (String) buildRegex.t(s);
			p = Pattern.compile(regexp, Pattern.DOTALL);
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			return p.matcher(str).matches();
		}
	}
}
