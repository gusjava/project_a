package a.entity.gus06.sys.translator1.builder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160225";}


	private Service load;


	public EntityImpl() throws Exception
	{
		load = Outside.service(this,"gus06.sys.translator1.loadresource");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		Object resource = o[0];
		G localeG = (G) o[1];
		String locale0 = (String) o[2];
		
		R r = (R) load.t(resource);
		
		return new R1(r,localeG,locale0);
	}
	
	
	
	private class R1 implements R, T
	{
		private R r;
		private G localeG;
		private String locale0;
		
		public R1(R r, G localeG, String locale0)
		{
			this.r = r;
			this.localeG = localeG;
			this.locale0 = locale0;
		}
		
		public Object r(String key) throws Exception
		{
			if(key==null) return "[null]";
			
			String locale = findLocale(localeG,locale0);
			return translate(key,locale,r);
		}
		
		public Object t(Object obj) throws Exception
		{
			if(obj==null) return "[null]";
			
			String locale = findLocale(localeG,locale0);
			if(obj instanceof String) return translate((String) obj,locale,r);
			
			return "["+obj.getClass().getName()+"]";
		}
	}
	
	
	private String findLocale(G localeG, String locale0) throws Exception
	{
		if(localeG==null) return locale0;
		String l = (String) localeG.g();
		return l==null || l.equals("") ? locale0 : l;
	}
	
	
	
	
	
	private String translate(String s, String locale, R r) throws Exception
	{
		if(s.startsWith("["))
			return translate1(s,locale);
		return translate2(s,locale,r);
	}
	
	private String translate1(String s, String locale)
	{
		String[] nn = s.split("\n");
		for(String n:nn) if(n.startsWith("["+locale+"]"))
			return n.substring(locale.length()+2);
		return s;
	}
	
	private String translate2(String s, String locale, R r) throws Exception
	{
		if(r==null) return s;
		String v = (String) r.r(s+"."+locale);
		return v==null ? s : v;
	}
}
