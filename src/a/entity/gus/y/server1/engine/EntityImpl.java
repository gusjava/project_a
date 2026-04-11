package a.entity.gus.y.server1.engine;

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
	private Service cmdKSql;
	private Service cmdEntity;
	private Service cmdScript;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this, "gus.y.server1.parser");
		buildJson = Outside.service(this, "gus.x.json.build1");
		cmdHello = Outside.service(this, "gus.y.server1.engine.cmd.hello");
		cmdHelp = Outside.service(this, "gus.y.server1.engine.cmd.help");
		cmdExit = Outside.service(this, "gus.y.server1.engine.cmd.exit");
		cmdRestart = Outside.service(this, "gus.y.server1.engine.cmd.restart");
		cmdInfos = Outside.service(this, "gus.y.server1.engine.cmd.infos");
		cmdThreads = Outside.service(this, "gus.y.server1.engine.cmd.threads");
		cmdMemory = Outside.service(this, "gus.y.server1.engine.cmd.memory");
		cmdErrors = Outside.service(this, "gus.y.server1.engine.cmd.errors");
		cmdMain = Outside.service(this, "gus.y.server1.engine.cmd.main");
		cmdResource = Outside.service(this, "gus.y.server1.engine.cmd.resource");
		cmdConfig = Outside.service(this, "gus.y.server1.engine.cmd.config");
		cmdCoreid = Outside.service(this, "gus.y.server1.engine.cmd.coreid");
		cmdK = Outside.service(this, "gus.y.server1.engine.cmd.k");
		cmdKSql = Outside.service(this, "gus.y.server1.engine.cmd.k_sql");
		cmdEntity = Outside.service(this, "gus.y.server1.engine.cmd.entity");
		cmdScript = Outside.service(this, "gus.y.server1.engine.cmd.script");
	}

	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		Map infos = (Map) parser.t(input);
		String cmd = (String) infos.get("cmd");
		List args = (List) infos.get("args");
		Object response = generate(cmd, args);
		return buildJson.t(response);
	}

	private Object generate(String cmd, List args) throws Exception
	{
		if(cmd.equals("hello")) return cmdHello.t(args);
		if(cmd.equals("help")) return cmdHelp.t(args);
		if(cmd.equals("exit")) return cmdExit.t(args);
		if(cmd.equals("restart")) return cmdRestart.t(args);
		if(cmd.equals("infos")) return cmdInfos.t(args);
		if(cmd.equals("threads")) return cmdThreads.t(args);
		if(cmd.equals("memory")) return cmdMemory.t(args);
		if(cmd.equals("errors")) return cmdErrors.t(args);
		if(cmd.equals("main")) return cmdMain.t(args);
		if(cmd.equals("resource")) return cmdResource.t(args);
		if(cmd.equals("config")) return cmdConfig.t(args);
		if(cmd.equals("coreId")) return cmdCoreid.t(args);
		if(cmd.equals("k")) return cmdK.t(args);
		if(cmd.equals("k-sql")) return cmdKSql.t(args);
		if(cmd.startsWith("@")) return cmdEntity.t(new Object[]{cmd.substring(1), args});
		if(cmd.startsWith("#")) return cmdScript.t(new Object[]{cmd.substring(1), args});
		return null;
	}
}
