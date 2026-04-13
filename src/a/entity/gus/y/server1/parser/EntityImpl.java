package a.entity.gus.y.server1.parser;

import java.util.HashMap;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260409";}
	
	public static final String CMD = "cmd";
	public static final String ARGS = "args";

	private Service parseArgs;

	public EntityImpl() throws Exception {
		
		parseArgs = Outside.service(this,"gus.y.server1.parser.args");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] n = s.split(" +",2);
		String cmd = n[0];
		String args = n.length>1 ? n[1] : null;
		Object argList = parseArgs.t(args);
		
		Map map = new HashMap();
		map.put(CMD, cmd);
		map.put(ARGS, argList);
		return map;
	}
}
