package a.entity.gus06.sys.script1.context.builder1.a.execution;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160328";}
	
	public static final String X_HISTORY = "history";
	public static final String X_CURRENT = "current";
	public static final String X_START = "start";
	public static final String X_END = "end";
	public static final String X_STOP = "stop";
	public static final String X_PARENT = "parent";
	public static final String X_SCRIPT = "script";


	private Service buildMap;
	private Service buildList;
	
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.map1");
		buildList = Outside.service(this,"gus06.list.list1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map context = (Map) obj;
		
		Map map = map("execution");
		
		map.put(X_HISTORY,list("history"));
		//map.put(X_CURRENT,null);
		//map.put(X_START,null);
		//map.put(X_PARENT,null);
		//map.put(X_SCRIPT,null);
		
		return map;
	}
	
	
	private Map map(String name) throws Exception
	{return (Map) buildMap.t(name);}
	
	private List list(String name) throws Exception
	{return (List) buildList.t(name);}
}
