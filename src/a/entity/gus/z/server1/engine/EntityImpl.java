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
	
	private File rootDir;

	public EntityImpl() throws Exception
	{
		parser = Outside.service(this,"gus.z.server1.parser");
		buildJson = Outside.service(this,"gus.x.json.build1");
		rootDir = (File) Outside.resource(this,"rootdir");
	}
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		Map infos = (Map) parser.t(input);
		
		String cmd = (String) infos.get(CMD);
		List args = (List) infos.get(ARGS);
		
		Map response = generateResponse(cmd, args);
		return buildJson.t(response);
	}
	
	private Map generateResponse(String cmd, List args) throws Exception
	{
		//TODO generate response
		return new HashMap();
	}
}
