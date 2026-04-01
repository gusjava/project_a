package a.entity.gus06.sys.script1.tool.execute.params.handler2.a.args;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160221";}

	public static final String K_ARGS = "args";


	private Service buildData;
	
	public EntityImpl() throws Exception
	{
		buildData = Outside.service(this,"gus06.sys.script1.tool.execute.params.handler2.a");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = (Map) o[0];
		Map tag = (Map) o[1];
		
		Map data = (Map) buildData.t(new Object[]{context,tag});
		return get(data,K_ARGS);
	}
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}