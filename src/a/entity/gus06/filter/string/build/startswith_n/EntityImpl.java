package a.entity.gus06.filter.string.build.startswith_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service toString;
	private Service charNormalize;
	
	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
		charNormalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
	}



	private String normalize(String s) throws Exception
	{return (String) charNormalize.t(s);}

	
	public Object t(Object obj) throws Exception
	{
		String s = normalize((String) obj);
		return new Filter(s);
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
			return str.startsWith(value);
		}
	}
}
