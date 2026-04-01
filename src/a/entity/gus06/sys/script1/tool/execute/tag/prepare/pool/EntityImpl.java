package a.entity.gus06.sys.script1.tool.execute.tag.prepare.pool;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160326";}
	
	public static final String P_POOL = "pool";
	public static final String P_CONTEXT = "context";
	public static final String P_RESERVED = "reserved";



	private Service reserved;
	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		reserved = Outside.service(this,"gus06.sys.script1.tool.execute.tag.prepare.reserved");
		buildMap = Outside.service(this,"gus06.map.map1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		
		Map pool = map("pool");
		
		pool.put(P_POOL,pool);
		pool.put(P_CONTEXT,context);
		pool.put(P_RESERVED,reserved.g());
		
		return pool;
	}
	
	
	private Map map(String name) throws Exception
	{return (Map) buildMap.t(name);}
}