package a.entity.gus.y.server1.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260407";}

	private Service parser;
	private Service buildJson;
	private Service cmdHello;
	private Service cmdHelp;
	private Service cmdExit;
	private Service cmdRestart;
	private Service cmdInfos;
	private Service cmdThreads;
	private Service cmdMemory;
	private Service cmdErrors;
	private Service cmdMain;
	private Service cmdResource;
	private Service cmdConfig;
	private Service cmdCoreid;
	private Service cmdK;
	private Service cmdE;
	private Service cmdR;
	private Service cmdEntity;
	private Service cmdScript;

	public EntityImpl() throws Exception
	{
		parser    = Outside.service(this, "gus.y.server1.parser");
		buildJson = Outside.service(this, "gus.x.json.build1");
		cmdHello    = Outside.service(this, "gus.y.server1.engine.cmd.hello");
		cmdHelp     = Outside.service(this, "gus.y.server1.engine.cmd.help");
		cmdExit     = Outside.service(this, "gus.y.server1.engine.cmd.exit");
		cmdRestart  = Outside.service(this, "gus.y.server1.engine.cmd.restart");
		cmdInfos    = Outside.service(this, "gus.y.server1.engine.cmd.infos");
		cmdThreads  = Outside.service(this, "gus.y.server1.engine.cmd.threads");
		cmdMemory   = Outside.service(this, "gus.y.server1.engine.cmd.memory");
		cmdErrors   = Outside.service(this, "gus.y.server1.engine.cmd.errors");
		cmdMain     = Outside.service(this, "gus.y.server1.engine.cmd.main");
		cmdResource = Outside.service(this, "gus.y.server1.engine.cmd.resource");
		cmdConfig   = Outside.service(this, "gus.y.server1.engine.cmd.config");
		cmdCoreid   = Outside.service(this, "gus.y.server1.engine.cmd.coreid");
		cmdK        = Outside.service(this, "gus.y.server1.engine.cmd.k");
		cmdE        = Outside.service(this, "gus.y.server1.engine.cmd.e");
		cmdR        = Outside.service(this, "gus.y.server1.engine.cmd.r");
		cmdEntity   = Outside.service(this, "gus.y.server1.engine.cmd.entity");
		cmdScript   = Outside.service(this, "gus.y.server1.engine.cmd.script");
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
		Object args = payload.get("args");
		
		if(cmd.startsWith("@"))    return cmdEntity.t(new Object[]{cmd.substring(1), args});
		if(cmd.startsWith("#"))    return cmdScript.t(new Object[]{cmd.substring(1), args});
		
		T builder = findBuilder(cmd);
		return builder.t(payload);
	}
	
	private T findBuilder(String cmd) throws Exception
	{
		if(cmd.equals("hello"))    return cmdHello;
		if(cmd.equals("help"))     return cmdHelp;
		if(cmd.equals("exit"))     return cmdExit;
		if(cmd.equals("restart"))  return cmdRestart;
		if(cmd.equals("infos"))    return cmdInfos;
		if(cmd.equals("threads"))  return cmdThreads;
		if(cmd.equals("memory"))   return cmdMemory;
		if(cmd.equals("errors"))   return cmdErrors;
		if(cmd.equals("main"))     return cmdMain;
		if(cmd.equals("resource")) return cmdResource;
		if(cmd.equals("config"))   return cmdConfig;
		if(cmd.equals("coreId"))   return cmdCoreid;
		if(cmd.equals("k"))        return cmdK;
		if(cmd.equals("e"))        return cmdE;
		if(cmd.equals("r"))        return cmdR;
		
		throw new Exception("Unsupported cmd: "+cmd);
	}
}
