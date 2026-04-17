package a.entity.gus.y.server1.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260407";}

	private Service parser;
	private Service buildJson;
	private Service callEntity;
	private Service callScript;
	private Service cmdMap;

	public EntityImpl() throws Exception
	{
		parser    = Outside.service(this, "gus.y.server1.parser");
		buildJson = Outside.service(this, "gus.x.json.build1");
		callEntity   = Outside.service(this, "gus.y.server1.engine.call.entity");
		callScript   = Outside.service(this, "gus.y.server1.engine.call.script");
		cmdMap     = Outside.service(this, "gus.y.server1.engine.cmdmap");
	}

	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		Map payload = (Map) parser.t(input);
		Object response = handlePayload(payload);
		return buildJson.t(response);
	}

	private Object handlePayload(Map payload) throws Exception
	{
		List cmds = (List) payload.get("cmds");
		String cmd = (String) cmds.get(0);
		
		if(cmd.startsWith("@"))    return callEntity.t(callInput(payload));
		if(cmd.startsWith("#"))    return callScript.t(callInput(payload));
		
		return findCmd(cmd).t(payload);
	}
	
	private T findCmd(String cmd) throws Exception
	{
		Map map = (Map) cmdMap.g();
		if(!map.containsKey(cmd))
			throw new Exception("Unsupported cmd: "+cmd);
		return (T) map.get(cmd);
	}
	
	private Object callInput(Map payload)
	{
		List cmds = (List) payload.get("cmds");
		Object args = payload.get("args");
		
		String cmd = (String) cmds.get(0);
		return new Object[]{cmd.substring(1), args};
	}
}
