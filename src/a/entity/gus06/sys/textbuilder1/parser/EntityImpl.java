package a.entity.gus06.sys.textbuilder1.parser;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160302";}
	
	public static final String KEY = "key";
	
	
	private Service findRules;
	private Service findFormats;
	private Service findDefaults;
	private Service matchText;
	
	public EntityImpl() throws Exception
	{
		findRules = Outside.service(this,"gus06.sys.textbuilder1.parser.findrules");
		findFormats = Outside.service(this,"gus06.sys.textbuilder1.parser.findformats");
		findDefaults = Outside.service(this,"gus06.sys.textbuilder1.parser.finddefaults");
		matchText = Outside.service(this,"gus06.sys.textbuilder1.parser.matchtext");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new T1((Map) obj);}
	
	
	
	
	private class T1 implements T
	{
		private Map mapRules;
		private Map mapFormats;
		private Map mapDefaults;
		
		public T1(Map map) throws Exception
		{
			this.mapRules = (Map) findRules.t(map);
			this.mapFormats = (Map) findFormats.t(map);
			this.mapDefaults = (Map) findDefaults.t(map);
		}
		
		public Object t(Object obj) throws Exception
		{
			String text = (String) obj;
			
			Iterator it = mapRules.keySet().iterator();
			while(it.hasNext())
			{
				String key = (String) it.next();
				String rule = (String) mapRules.get(key);
				
				Map data = (Map) matchText.t(new Object[]{text,rule,mapFormats});
				
				if(data!=null)
				{
					data.put(KEY,key);
					addDefaults(data,mapDefaults);
					return data;
				}
			}
			return null;
		}
	}
	
	
	
	
	private void addDefaults(Map data, Map mapDefaults)
	{
		Iterator it = mapDefaults.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			if(!data.containsKey(key))
			data.put(key,mapDefaults.get(key));
		}
	}
}
