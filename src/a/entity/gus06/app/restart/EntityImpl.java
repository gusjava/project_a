package a.entity.gus06.app.restart;

import java.io.File;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20140705";}


	private Service appLocation;
	private Service getClasspath;
	private Service launchJar;
	private Service launchBin;
	private Service exit;

	public EntityImpl() throws Exception
	{
		appLocation  = Outside.service(this, "gus.x.app.location");
		getClasspath = Outside.service(this, "gus.y.entityclass1.cl.classpath");
		launchJar    = Outside.service(this, "gus06.java.launchjar");
		launchBin    = Outside.service(this, "gus06.java.launchbin");
		exit         = Outside.service(this, "gus.y.app1.execute.exit");
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
		File location = (File) appLocation.g();
		boolean isJar = location.getName().endsWith(".jar");

		if(!map.containsKey("options"))
		{
			long xmx = Runtime.getRuntime().maxMemory();
			String options = "-Xms"+xmx+" -Xmx"+xmx;
			map.put("options",options);
		}

		if(isJar)
		{
			if(!map.containsKey("jar")) map.put("jar",location);
			launchJar.p(map);
		}
		else
		{
			if(!map.containsKey("classpath"))
			{
				File[] cp = (File[]) getClasspath.g();
				map.put("classpath",cp);
			}
			if(!map.containsKey("mainclass"))
			{
				Class mc = (Class) Outside.resource(this,"launch.class");
				map.put("mainclass",mc);
			}
			launchBin.p(map);
		}

		exit.e();
		System.exit(0);
	}
}
