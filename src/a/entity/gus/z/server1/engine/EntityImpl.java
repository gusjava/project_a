package a.entity.gus.z.server1.engine;

import java.io.File;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260407";}
	
	public static final String CMD = "cmd";
	public static final String ARGS = "args";
	public static final String WELCOME = "Hello! Nice to meet you! I am gus.server1, a Java application based on A project.";


	private Service parser;
	private Service buildJson;
	private Service uniqueentity;
	private Service execute1s;
	private Service restart;
	private Service exit;
	
	private File rootDir;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus.z.server1.parser");
		buildJson = Outside.service(this,"gus.x.json.build1");
		uniqueentity = Outside.service(this,"uniqueentity");
		execute1s = Outside.service(this,"gus.x.execute.th.delay1s");
		restart = Outside.service(this,"gus06.app.restart0");
		exit = Outside.service(this,"gus06.app.execute.exit");
		
		rootDir = (File) Outside.resource(this,"rootdir");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		Map infos = (Map) parser.t(input);
		
		String cmd = (String) infos.get(CMD);
		List args = (List) infos.get(ARGS);
		
		Object response = generate(cmd, args);
		return buildJson.t(response);
	}
	
	private Object generate(String cmd, List args) throws Exception
	{
		if(cmd.equals("hello")) return WELCOME;
		if(cmd.equals("exit")) return exit();
		if(cmd.equals("restart")) return restart();
		
		if(cmd.startsWith("@")) return generateFromEntity(cmd.substring(1), args);
		if(cmd.startsWith("#")) return generateFromScript(cmd.substring(1), args);
		
		//TODO other ?
		return null;
	}
	
	private Object generateFromEntity(String entityName, List args) throws Exception
	{
		T entity = (T) uniqueentity.t(entityName);
		if(args.size()==1) return entity.t(args.get(0));
		return entity.t(args);
	}
	
	private Object generateFromScript(String scriptName, List args) throws Exception
	{
		return new HashMap();
	}
	
	private String exit() throws Exception
	{
		execute1s.p(exit);
		return "Bye.";
	}
	
	private String restart() throws Exception
	{
		execute1s.p(restart);
		return "Restarting...";
	}
}
