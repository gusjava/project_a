package a.entity.gus.y.server1.engine.cmdmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20260417";}
	
	private Service cmdHello;
	private Service cmdHelp;
	private Service cmdConfig;
	private Service cmdCoreid;
	private Service cmdInfos;
	
	private Service cmdExit;
	private Service cmdRestart;
	
	private Service cmdThreads;
	private Service cmdThread;
	private Service cmdMemory;
	private Service cmdErrors;
	private Service cmdMain;
	private Service cmdProp;
	private Service cmdResource;
	
	private Service cmdK;
	private Service cmdE;
	private Service cmdR;
	
	private Service cmdKSql;
	private Service cmdESql;
	private Service cmdRSql;
	
	private Map cmdMap;

	public EntityImpl() throws Exception
	{
		cmdHello    = Outside.service(this, "gus.y.server1.engine.cmd.hello");
		cmdHelp     = Outside.service(this, "gus.y.server1.engine.cmd.help");
		cmdConfig   = Outside.service(this, "gus.y.server1.engine.cmd.config");
		cmdCoreid   = Outside.service(this, "gus.y.server1.engine.cmd.coreid");
		cmdInfos    = Outside.service(this, "gus.y.server1.engine.cmd.infos");
		
		cmdThreads  = Outside.service(this, "gus.y.server1.engine.cmd.threads");
		cmdThread  = Outside.service(this, "gus.y.server1.engine.cmd.thread");
		cmdMemory   = Outside.service(this, "gus.y.server1.engine.cmd.memory");
		cmdErrors   = Outside.service(this, "gus.y.server1.engine.cmd.errors");
		cmdMain     = Outside.service(this, "gus.y.server1.engine.cmd.main");
		cmdProp     = Outside.service(this, "gus.y.server1.engine.cmd.prop");
		cmdResource = Outside.service(this, "gus.y.server1.engine.cmd.resource");
		
		cmdExit     = Outside.service(this, "gus.y.server1.engine.cmd.exit");
		cmdRestart  = Outside.service(this, "gus.y.server1.engine.cmd.restart");
		
		cmdK        = Outside.service(this, "gus.y.server1.engine.cmd.k");
		cmdE        = Outside.service(this, "gus.y.server1.engine.cmd.e");
		cmdR        = Outside.service(this, "gus.y.server1.engine.cmd.r");
		
		cmdKSql     = Outside.service(this, "gus.y.server1.engine.cmd.k.sql");
		cmdESql     = Outside.service(this, "gus.y.server1.engine.cmd.e.sql");
		cmdRSql     = Outside.service(this, "gus.y.server1.engine.cmd.r.sql");
	}

	public Object g() throws Exception
	{
		if(cmdMap==null) initCmdMap();
		return cmdMap;
	}
	
	private void initCmdMap()
	{
		cmdMap = new HashMap();

		cmdMap.put("hello", cmdHello);
		cmdMap.put("help", cmdHelp);
		cmdMap.put("config", cmdConfig);
		cmdMap.put("coreId", cmdCoreid);
		cmdMap.put("infos", cmdInfos);
		
		cmdMap.put("threads", cmdThreads);
		cmdMap.put("thread", cmdThread);
		cmdMap.put("memory", cmdMemory);
		cmdMap.put("errors", cmdErrors);
		cmdMap.put("main", cmdMain);
		cmdMap.put("prop", cmdProp);
		cmdMap.put("resource", cmdResource);
		
		cmdMap.put("exit", cmdExit);
		cmdMap.put("restart", cmdRestart);
		
		cmdMap.put("e", cmdE);
		cmdMap.put("k", cmdK);
		cmdMap.put("r", cmdR);
		
		cmdMap.put("e_sql", cmdESql);
		cmdMap.put("k_sql", cmdKSql);
		cmdMap.put("r_sql", cmdRSql);
	}
}
