package a.entity.gus06.filter.string.build.unicode.endswith;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150526";}


	private Service toString;
	
	public EntityImpl() throws Exception
	{
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Filter((String) obj);}
	
	
	
	private class Filter implements F
	{
		private CharTools.CharType charType;
		
		public Filter(String value)
		{charType = CharTools.buildCharType(value);}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String str = (String) toString.t(obj);
			if(str.equals("")) return false;
			
			char c = str.charAt(str.length()-1);
			return charType.isTypeOf(c);
		}
	}
}
