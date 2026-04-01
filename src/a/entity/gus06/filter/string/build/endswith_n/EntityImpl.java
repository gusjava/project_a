package a.entity.gus06.filter.string.build.endswith_n;

import a.framework.*;

public class EntityImpl implements Entity, T {


	public String creationDate() {return "20150526";}


	private Service charNormalize;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		charNormalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	private String normalize(String s) throws Exception
	{return (String) charNormalize.t(s);}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return new Filter(normalize(s));
	}
	
	
	private class Filter implements F
	{
		private String value;
		public Filter(String value)
		{this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = normalize((String) toString.t(obj));
			return str.endsWith(value);
		}
	}
}
