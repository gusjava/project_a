package a.entity.gus06.filter.string.build.mdots_n;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160406";}


	private Service prepare;
	private Service charNormalize;
	private Service toString;
	
	public EntityImpl() throws Exception
	{
		charNormalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");
		prepare = Outside.service(this,"gus06.filter.string.build.mdots.prepare");
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
		private String start;
		private String end;
		private boolean startInv;
		private boolean endInv;
		
		public Filter(String s) throws Exception
		{
			Object[] t = (Object[]) prepare.t(s);
			if(t.length!=4) throw new Exception("Wrong data number: "+t.length);
			
			start = (String) t[0];
			end = (String) t[1];
			startInv = ((Boolean) t[2]).booleanValue();
			endInv = ((Boolean) t[3]).booleanValue();
		}
		
		public boolean f(Object obj) throws Exception
		{
			if(obj==null) return false;
			String s0 = normalize((String) toString.t(obj));
			
			return s0.startsWith(start)^startInv && s0.endsWith(end)^endInv;
		}
	}
}
