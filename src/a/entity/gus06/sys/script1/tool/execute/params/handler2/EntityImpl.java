package a.entity.gus06.sys.script1.tool.execute.params.handler2;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160126";}
   
	public static final String K_OPTIONS = "options";
	
	public static final String[] KEYS = new String[]{
				"mode",
				"indent",
				"args",
				"if"};

	public static final String[] RULES = new String[]{
				"with [options]",
				"mode [mode]",
				"indent [indent]",
				"args [args]",
				"if [if]"};



	private Service evalAsObject;
   
	public EntityImpl() throws Exception
	{
		evalAsObject = Outside.service(this,"gus06.sys.script1.context.evaluate.withrule");
	}
   
   
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
	   
		Map context = (Map) o[0];
		String params = (String) o[1];
	   	
		Map m = new HashMap();
	   	
		if(params!=null && !params.equals(""))
		{
			Map data = (Map) evalAsObject.t(new Object[]{context,params,RULES});
			
			for(String key:KEYS) transfer(data,m,key);
			
			Map options = (Map) get(data,K_OPTIONS);
			if(options!=null) m.putAll(options);
		}
		
		return m;
	}
   
   
   
   
   
	private boolean has(Map m, String key)
	{
		if(m==null || key==null) return false;
		return m.containsKey(key);
	}
   
	private Object get(Map m, String key)
	{
		if(m==null || key==null) return null;
		if(!m.containsKey(key)) return null;
		return m.get(key);
	}
   
	private void transfer(Map m1, Map m2, String key)
	{
		if(!has(m1,key)) return;
		if(m2==null) return;
		m2.put(key,m1.get(key));
	}
}