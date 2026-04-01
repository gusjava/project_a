package a.entity.gus06.appli.gusclient1.project.config.load2.prop;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150311";}

	public static final String FILENAME = "prop";
	
	private Service loadConfig;


	public EntityImpl() throws Exception
	{loadConfig = Outside.service(this,"gus06.appli.gusclient1.project.config.load2");}
	
	
	public Object g() throws Exception
	{return loadConfig.r(FILENAME);}
}
