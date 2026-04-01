package a.entity.gus06.sys.store.map.find;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T, R {

	public String creationDate() {return "20140907";}


	public static final String KEY_RULE = "_rule";
	
	
	
	private Service getMap;
	private Service applyModel;
	private Service applyArgs;
	
	
	public EntityImpl() throws Exception
	{
		getMap = Outside.service(this,"gus06.sys.store.map.base");
		applyModel = Outside.service(this,"gus06.sys.store.map.applymodel");
		applyArgs = Outside.service(this,"gus06.sys.store.map.applyargs");
	}
	
	
	public Object r(String key) throws Exception
	{return findMap(key);}
	
	
	public Object t(Object obj) throws Exception
	{return findMap((String) obj);}
	
	
	
	private Map findMap(String rule) throws Exception
	{
		try
		{
			String args = rule.contains("|")?rule.split("\\|",2)[1]:null;
			String seq = rule.contains("|")?rule.split("\\|",2)[0]:rule;
    		
			Map map = findMap_fromSeq(seq);
			if(map==null || map.isEmpty()) return null;
        	
			map.put(KEY_RULE,rule);
			if(args!=null) applyArgs.v(args,map);
			return map;
		}
		catch(Exception e)
		{
			String message = "findMap failed for rule: "+rule;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	private Map findMap_fromSeq(String seq) throws Exception
	{
		String[] ids = seq.split(";");
    		
		Map map = new HashMap();
		for(String id:ids)
		map.putAll(findMap_fromId(id));
    		
		return map;
	}
	
	
	
	private Map findMap_fromId(String id) throws Exception
	{
		Map map = getMap(id);
		return (Map) applyModel.t(map);
	}
	
	
	
	private Map getMap(String id) throws Exception
	{
		Map map = (Map) getMap.r(id);
		if(map==null || map.isEmpty()) throw new Exception("Map not found for id: "+id);
		return map;
	}
}

