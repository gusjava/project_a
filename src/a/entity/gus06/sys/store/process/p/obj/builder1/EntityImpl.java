package a.entity.gus06.sys.store.process.p.obj.builder1;

import a.framework.*;
import java.util.Map;


public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140907";}


	public static final String KEY_TYPE = "type";
	
	public static final String KEY_MAIN = "main";
	public static final String KEY_INPUT = "input";
	public static final String KEY_CUST = "cust";
	
	public static final String KEY_KEEPMAIN = "keepmain";
	public static final String KEY_KEEPINPUT = "keepinput";
	public static final String KEY_KEEPCUST = "keepcust";
	
	public static final String KEY_RECALLMAIN = "recallmain";
	public static final String KEY_RECALLINPUT = "recallinput";
	public static final String KEY_RECALLCUST = "recallcust";
	
	
	
	
	
	private Service findObj;
	private Service keeper;
	private Service builder2;
	private Service subMap;
	
	public EntityImpl() throws Exception
	{
		findObj = Outside.service(this,"gus06.sys.store.obj.find");
		keeper = Outside.service(this,"gus06.sys.store.process.p.obj.keeper");
		builder2 = Outside.service(this,"gus06.sys.store.process.p.obj.builder2");
		subMap = Outside.service(this,"gus06.map.string.submap");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;

		String type = get(map,KEY_TYPE,null);
		if(type==null) throw new Exception("Key not found: "+KEY_TYPE);
	
		Object main = findMain(map);
		Object input = findInput(map);
		Object cust = findCust(map);
   	
		Object output = builder2.t(new Object[]{type,main,input});
		if(cust!=null) ((P)cust).p(output);
		
		return output;
	}





	private Object findMain(Map map) throws Exception
	{
		Object main = recall(map,KEY_KEEPMAIN,KEY_RECALLMAIN);
		if(main!=null) return main;
		
		main = buildMain(map);
		keep(map,KEY_KEEPMAIN,main);
		return main;
	}
	
	private Object findInput(Map map) throws Exception
	{
		Object input = recall(map,KEY_KEEPINPUT,KEY_RECALLINPUT);
		if(input!=null) return input;
		
		input = buildInput(map);
		keep(map,KEY_KEEPINPUT,input);
		return input;
	}
	
	private Object findCust(Map map) throws Exception
	{
		Object cust = recall(map,KEY_KEEPCUST,KEY_RECALLCUST);
		if(cust!=null) return cust;
		
		cust = buildCust(map);
		keep(map,KEY_KEEPCUST,cust);
		return cust;
	}
	
	
	
	
	
	
	
	private Object buildMain(Map map) throws Exception
	{
		String rule = get(map,KEY_MAIN,null);
		if(rule==null || rule.equals("")) throw new Exception("Invalid null main value");
		return findObj.t(rule);
	}

	private Object buildInput(Map map) throws Exception
	{
		String rule = get(map,KEY_INPUT,null);
		if(rule==null || rule.equals("")) return null;
		if(rule.startsWith("map ")) return subMap(map,rule.substring(4));
		if(rule.equals("map")) return map;
		return findObj.t(rule);
	}

	private Object buildCust(Map map) throws Exception
	{
		String rule = get(map,KEY_CUST,null);
		if(rule==null || rule.equals("")) return null;
		return findObj.t(rule);
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
	
	private String get(Map map, String key, String defaultValue)
	{return has(map,key)?get(map,key):defaultValue;}
	
	private boolean isTrue(Map map, String key)
	{return get(map,key).equals("true");}
	
	private Map subMap(Map map, String key) throws Exception
	{return (Map) subMap.t(new Object[]{map,key});}
}
