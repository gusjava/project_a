package a.entity.gus.y.server1.engine.cmd.help;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260410";}

	public Object t(Object obj) throws Exception
	{
		Map map = new HashMap();
		map.put("hello",                 "Welcome message");
		map.put("help",                  "Show this help");
		map.put("infos",                 "JVM and server info");
		map.put("threads",               "Snapshot of all JVM threads (id → info map)");
		map.put("memory",                "JVM memory usage (heap and non-heap)");
		map.put("errors",                "List of accumulated errors");
		map.put("restart",               "Restart the server");
		map.put("exit",                  "Stop the server");
		map.put("main [key]",            "Describe main map or a specific entry");
		map.put("resource <rule>",       "Call Outside.resource with the given rule");
		map.put("config",                "Current config ID");
		map.put("coreId",                "Current core ID");
		map.put("k ...",                 "Knowledge DB commands — use 'k help' for details");
		map.put("k-sql <sql>",           "Execute a raw SQL query on knowledgedb1 (select/insert/update/delete)");
		map.put("r ...",                 "Roadmap DB commands — use 'r help' for details");
		map.put("r-sql <sql>",           "Execute a raw SQL query on roadmapdb1 (select/insert/update/delete)");
		map.put("e ...",                 "Entity DB commands — use 'e help' for details");
		map.put("e-sql <sql>",           "Execute a raw SQL query on entitydb1 (select/insert/update/delete)");
		map.put("@entityName [args...]", "Invoke entity.t(arg) — entity must implement T");
		map.put("#scriptName [args...]", "Not implemented");
		return map;
	}
}