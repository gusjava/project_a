package a.entity.gus06.sys.store2.build.mapfinder;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160309";}

	
	
	private Service applyModel;
	private Service applyArgs;
	
	
	public EntityImpl() throws Exception
	{
		applyModel = Outside.service(this,"gus06.sys.store2.map.applymodel");
		applyArgs = Outside.service(this,"gus06.sys.store.map.applyargs");
	}
	
	
	
	
	public Object t(Object obj) throws Exception
	{return new T1((T) obj);}
	
	
	
	private class T1 implements T
	{
		private T t;
		public T1(T t) {this.t = t;}
		
		public Object t(Object obj) throws Exception
		{return findMap(t,(String) obj);}
	}
	
	
	
	private Map findMap(T t, String rule) throws Exception
	{
		try
		{
			String args = rule.contains("|")?rule.split("\\|",2)[1]:null;
			String seq = rule.contains("|")?rule.split("\\|",2)[0]:rule;
    		
			Map map = findMap_fromSeq(t,seq);
			if(map==null || map.isEmpty()) return null;
        	
			if(args!=null) applyArgs.v(args,map);
			return map;
		}
		catch(Exception e)
		{
			String message = "findMap failed for rule: "+rule;
			throw new Exception(message,e);
		}
	}
	
	
	
	
	
	private Map findMap_fromSeq(T t, String seq) throws Exception
	{
		String[] ids = seq.split(";");
    	
		Map map = new HashMap();
		for(String id:ids)
		map.putAll(findMap_fromId(t,id));
    	
		return map;
	}
	
	
	
	private Map findMap_fromId(T t, String id) throws Exception
	{
		Map map = getMap(t,id);
		return (Map) applyModel.t(new Object[]{t,map});
	}
	
	
	
	private Map getMap(T t, String id) throws Exception
	{
		Map map = (Map) t.t(id);
		if(map==null) throw new Exception("Map not found for id: "+id);
		return map;
	}
}

