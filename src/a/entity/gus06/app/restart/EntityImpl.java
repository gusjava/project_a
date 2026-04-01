package a.entity.gus06.app.restart;

import java.io.File;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20140705";}

	
	private Service findJar;
	private Service launchJar;
	private Service exit;

	public EntityImpl() throws Exception
	{
		findJar = Outside.service(this,"gus06.app.jarfile");
		launchJar = Outside.service(this,"gus06.java.launchjar");
		exit = Outside.service(this,"gus06.app.execute.exit");
	}
	
	
	public void e() throws Exception
	{p(null);}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) handleNull();
		else if(obj instanceof String) handleString((String) obj);
		else if(obj instanceof Map) handleMap((Map) obj);
		else throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void handleNull() throws Exception
	{
		Map map = new HashMap();
		handleMap(map);
	}
	
	private void handleString(String args) throws Exception
	{
		Map map = new HashMap();
		map.put("args",args);
		handleMap(map);
	}
	
	private void handleMap(Map map) throws Exception
	{
		if(!map.containsKey("jar"))
		{
			File jar = (File) findJar.g();
			map.put("jar",jar);
		}
		
		if(!map.containsKey("options"))
		{
			long xmx = Runtime.getRuntime().maxMemory();
			String options = "-Xms"+xmx+" -Xmx"+xmx;
			map.put("options",options);
		}
		
		launchJar.p(map);
		exit.e();
		System.exit(0);
	}
}