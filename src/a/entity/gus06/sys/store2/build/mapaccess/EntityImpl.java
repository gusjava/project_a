package a.entity.gus06.sys.store2.build.mapaccess;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}

	private Service mapFinder;

	public EntityImpl() throws Exception
	{mapFinder = Outside.service(this,"gus06.sys.store2.build.mapfinder");}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}
	
	
	
	private class Holder implements T, R, V, F, G
	{
		private Object access;
		private T finder;
		
		public Holder(Object access) throws Exception
		{
			this.access = access;
			this.finder = (T) mapFinder.t(access);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.startsWith(":"))
				return finder.t(key.substring(1));
			return ((R) access).r(key);
		}
		
		public boolean f(Object obj) throws Exception
		{return ((F) access).f(obj);}
		
		public Object g() throws Exception
		{return ((G) access).g();}
		
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
	
		public void v(String key, Object obj) throws Exception
		{((V) access).v(key,obj);}
	}
}