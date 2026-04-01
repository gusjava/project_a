package a.entity.gus06.appli.gusclient1.project.config.load2;

import a.framework.*;
import java.io.File;
import java.util.Properties;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20150311";}


	private Service loadConfig;

	public EntityImpl() throws Exception
	{loadConfig = Outside.service(this,"gus06.appli.gusclient1.project.config.load");}
	
	
	public Object r(String key) throws Exception
	{return loadConfigComplete(key);}
	
	
	
	
	private Properties loadConfigComplete(String id) throws Exception
	{
		Properties p = loadConfig(id);
		if(!p.containsKey("model")) return p;
		
		String model = (String) p.get("model");
		p.remove("model");
		
		String[] nn = model.split(";");
		for(String n:nn) p.putAll(loadConfigComplete(n));
		
		return p;
	}
	
	
	
	private Properties loadConfig(String id) throws Exception
	{return (Properties) loadConfig.r(id);}
}
