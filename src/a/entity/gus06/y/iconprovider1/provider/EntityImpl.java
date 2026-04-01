package a.entity.gus06.y.iconprovider1.provider;

import a.framework.*;
import javax.swing.Icon;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250727";}

	private Service buildLoader;
	private Service buildBuilder;

	public EntityImpl() throws Exception
	{
		buildLoader = Outside.service(this,"gus06.y.iconprovider1.loader");
		buildBuilder = Outside.service(this,"gus06.y.iconprovider1.builder");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object loader = buildLoader.t(obj);
		Object builder = buildBuilder.t(loader);
		return new Provider((R) builder);
	}
	
	
	
	private class Provider implements T, R, E
	{
		private R iconBuilder;
		private Map cache;
		
		public Provider(R iconBuilder)
		{
			this.iconBuilder = iconBuilder;
			cache = new HashMap();
		}
	
		public void e() throws Exception
		{cache.clear();}
	
		public Object t(Object obj) throws Exception
		{return r((String) obj);}
		
		public Object r(String key) throws Exception
		{
			if(key==null || key.equals("")) return null;
		
			if(!cache.containsKey(key))
			{
				Object value = build(key);
				if(value==null) return null;
				cache.put(key,value);
			}
			return cache.get(key);
		}
		
		private Object build(String key) throws Exception
		{return iconBuilder.r(key);}
	}
}
