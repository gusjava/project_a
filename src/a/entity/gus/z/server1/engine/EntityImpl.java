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
	public static final String HELP =
		"Commands:\n" +
		"  hello                      Welcome message\n" +
		"  infos                      JVM and server info\n" +
		"  restart                    Restart the server\n" +
		"  exit                       Stop the server\n" +
		"  help                       Show this help\n" +
		"  main [key]                 Describe main map or a specific entry\n" +
		"  resource <rule>            Call Outside.resource with the given rule\n" +
		"  @entityName [args...]      Invoke entity.t(arg) — entity must implement T\n" +
		"  #scriptName [args...]      Not implemented";


	private Service parser;
	private Service buildJson;
	private Service uniqueentity;
	private Service execute1s;
	private Service restart;
	private Service exit;
	private Service infoMap;
	private Service buildDesc;
	
	private File rootDir;
	private Map main;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus.z.server1.parser");
		buildJson = Outside.service(this,"gus.x.json.build1");
		uniqueentity = Outside.service(this,"uniqueentity");
		execute1s = Outside.service(this,"gus.x.execute.th.delay1s");
		restart = Outside.service(this,"gus06.app.restart0");
		exit = Outside.service(this,"gus06.app.execute.exit");
		infoMap = Outside.service(this,"gus06.app.infomap");
		buildDesc = Outside.service(this,"gus06.tostring.desc");
		
		rootDir = (File) Outside.resource(this,"rootdir");
		main = (Map) Outside.resource(this,"main");
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
		if(cmd.equals("help")) return HELP;
		if(cmd.equals("exit")) return exit();
		if(cmd.equals("restart")) return restart();
		if(cmd.equals("infos")) return infos();
		if(cmd.equals("main")) return main(args);
		if(cmd.equals("resource")) return resource(args);

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
	
	private Object infos() throws Exception
	{
		return infoMap.g();
	}
	
	private Object resource(List args) throws Exception
	{
		String rule = String.join(" ", args);
		return buildDesc.t(Outside.resource(this, rule));
	}

	private Object main(List args) throws Exception
	{
		if(args.isEmpty()) return buildDesc.t(main);
		String key = (String) args.get(0);
		return buildDesc.t(main.get(key));
	}
}