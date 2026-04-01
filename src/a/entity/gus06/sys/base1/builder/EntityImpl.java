package a.entity.gus06.sys.base1.builder;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150329";}
	
	
	
	private Service accessBuilder;
	private Service searchBuilder;
	private Service transBuilder;
	private Service randomId;


	public EntityImpl() throws Exception
	{
		accessBuilder = Outside.service(this,"gus06.sys.base1.builder.access");
		searchBuilder = Outside.service(this,"gus06.sys.base1.builder.search");
		transBuilder = Outside.service(this,"gus06.sys.base1.builder.trans");
		randomId = Outside.service(this,"gus06.data.generate.string.random.number14");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Holder(obj);}
	
	
	
	
	
	private class Holder implements R, V, F, G, P, E
	{
		private Object source;
		private Object access;
		private Object search;
		private Object trans;
		
		private String lastId;
		
		public Holder(Object source) throws Exception
		{
			this.source = source;
			access = accessBuilder.t(source);
			search = searchBuilder.t(source);
			trans = transBuilder.t(source);
		}
		
		public Object r(String key) throws Exception
		{
			if(key.equals("source")) return source;
			if(key.equals("access")) return access;
			if(key.equals("search")) return search;
			if(key.equals("trans")) return trans;
			if(key.equals("lastId")) return lastId;
			
			String id = findId(key);
			if(id!=null)
			{
				Object entry = ((R)access).r(id);
				lastId = id;
				return entry;
			}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("map"))
			{
				String id = randomId();
				((V)access).v(id,obj);
				lastId = id;
				return;
			}
			
			String id = findId(key);
			if(id!=null)
			{
				((V)access).v(id,obj);
				lastId = id;
				return;
			}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String key = (String) obj;
			
			String id = findId(key);
			if(id!=null)
			{
				boolean found = ((F)access).f(id);
				lastId = id;
				return found;
			}
			
			throw new Exception("Unknown key: "+key);
		}
		
		public Object g() throws Exception
		{return ((G)access).g();}
		
		public void p(Object obj) throws Exception
		{((P) trans).p(obj);}
		
		public void e() throws Exception
		{((E) access).e();}
	}
	
	
	
	
	
	
	
	
	private String randomId() throws Exception
	{return (String) randomId.g();}
		
		
	private String findId(String s)
	{
		if(!s.startsWith("map_")) return null;
		return s.substring(4);
	}
}