package a.entity.gus06.appli.gusclient1.project.config.load.mapping;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150311";}

	public static final String FILENAME = "mapping";
	
	private Service loadConfig;


	public EntityImpl() throws Exception
	{loadConfig = Outside.service(this,"gus06.appli.gusclient1.project.config.load");}
	
	
	public Object g() throws Exception
	{return loadConfig.r(FILENAME);}
}
