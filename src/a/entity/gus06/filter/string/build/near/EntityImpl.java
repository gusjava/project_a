package a.entity.gus06.filter.string.build.near;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150529";}


	private Service comparator;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		comparator = Outside.service(this,"gus06.data.compare.string.comparator1");
		toString = Outside.service(this,"gus06.tostring.tostring1");
	}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
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
			String str = (String) toString.t(obj);
			return comparator.f(new String[]{str,value});
		}
	}
}
