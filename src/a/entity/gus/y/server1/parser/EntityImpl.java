package a.entity.gus.y.server1.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260409";}

	public static final String CMDS = "cmds";
	public static final String ARGS = "args";

	private Service parseArgs;

	public EntityImpl() throws Exception {
		parseArgs = Outside.service(this,"gus.y.server1.parser.args");
	}

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;

		String[] n = s.split(" +",2);
		String cmdStr = n[0];
		String argsStr = n.length>1 ? n[1] : null;

		List cmds = new ArrayList(Arrays.asList(cmdStr.split("-")));
		Object argList = parseArgs.t(argsStr);

		Map map = new HashMap();
		map.put(CMDS, cmds);
		map.put(ARGS, argList);
		return map;
	}
}
