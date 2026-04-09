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


	private Service parser;
	private Service buildJson;
	private Service uniqueentity;
	
	private File rootDir;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus.z.server1.parser");
		buildJson = Outside.service(this,"gus.x.json.build1");
		uniqueentity = Outside.service(this,"uniqueentity");
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
}
