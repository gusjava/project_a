package a.entity.gus06.sys.store.process.p.obj;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}

	public static final String KEY_KEEPMAP = "keepmap";
	public static final String KEY_KEEPOUTPUT = "keepoutput";
	public static final String KEY_RECALLOUTPUT = "recalloutput";
	


	private Service findMap;
	private Service keeper;
	private Service builder1;



	public EntityImpl() throws Exception
	{
		findMap = Outside.service(this,"gus06.sys.store.process.p.map");
		keeper = Outside.service(this,"gus06.sys.store.process.p.obj.keeper");
		builder1 = Outside.service(this,"gus06.sys.store.process.p.obj.builder1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) findMap.t(obj);
		keep(map,KEY_KEEPMAP,map);
		return finder(map);
	}
	
	
	
	
	
	private Object finder(Map map) throws Exception
	{
		Object output = recall(map,KEY_KEEPOUTPUT,KEY_RECALLOUTPUT);
		if(output!=null) return output;

		output = builder1.t(map);
		keep(map,KEY_KEEPOUTPUT,output);
		return output;
	}
    



	private void keep(Map map, String key, Object obj) throws Exception
	{
		if(obj==null || !has(map,key)) return;
		String keepId = get(map,key);
		keeper.v(keepId,obj);
	}
	
	
	private Object recall(Map map, String keepKey, String recallKey) throws Exception
	{
		if(!has(map,keepKey)) return null;
		if(!has(map,recallKey)) return null;
		if(!isTrue(map,recallKey)) return null;
		
		String keepId = get(map,keepKey);
		return keeper.r(keepId);
	}
	
	
	
	
	
	private boolean has(Map map, String key)
	{return map.containsKey(key);}
    
	private String get(Map map, String key)
	{return (String) map.get(key);}
	
	private boolean isTrue(Map map, String key)
	{return get(map,key).equals("true");}
}
