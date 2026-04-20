package a.entity.gus.y.server1.tool.helpmap;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260417";}
	
	private Map map;

	public Object g() throws Exception
	{
		if(map==null) initMap();
		return map;
	}
	
	private void initMap()
	{
		map = new HashMap();
		
		map.put("hello",                 "Welcome message");
		map.put("help [cmd]",            "Show this help map or a specific entry");
		map.put("config",                "Current config ID");
		map.put("coreId",                "Current core ID");
		map.put("infos",                 "JVM and server info");
		
		map.put("threads",               "Snapshot of all JVM threads");
		map.put("thread",                "Detailed snapshot of one JVM thread (id → info map)");
		map.put("memory",                "JVM memory usage (heap and non-heap)");
		map.put("errors",                "List of accumulated errors");
		map.put("main [key]",            "Describe main map or a specific entry");
		map.put("prop [key]",            "Property map or a specific entry");
		map.put("resource <rule>",       "Call Outside.resource with the given rule");
		
		map.put("exit",                  "Exit the application");
		map.put("restart",               "Restart the application");
		
		map.put("e ...",                 "Entity DB commands — use 'e help' for details");
		map.put("k ...",                 "Knowledge DB commands — use 'k help' for details");
		map.put("r ...",                 "Roadmap DB commands — use 'r help' for details");
		
		map.put("e_sql <sql>",           "Execute a raw SQL query on entitydb1 (select/insert/update/delete)");
		map.put("k_sql <sql>",           "Execute a raw SQL query on knowledgedb1 (select/insert/update/delete)");
		map.put("r_sql <sql>",           "Execute a raw SQL query on roadmapdb1 (select/insert/update/delete)");
		
		map.put("@entityName [args...]", "Invoke entity.t(arg) — entity must implement T");
		map.put("@entityName :<json>",   "Invoke entity.t(parsedJson) — pass a structured object (Map, List, String…)");
		map.put("#scriptName [args...]", "Not implemented");
	}
}
