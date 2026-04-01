package a.entity.gus06.app.mainframe.defaulttitle;

import a.framework.*;
import javax.swing.JFrame;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140826";}


	private String title;
	private String version;
	private String buildTime;

	public EntityImpl() throws Exception
	{
		title = (String) Outside.resource(this,"property#app.title");
		version = (String) Outside.resource(this,"property#app.version");
		buildTime = (String) Outside.resource(this,"property#jar.buildtime");
	}
	
	public Object g() throws Exception
	{
		if(version==null) return title;
		if(version.endsWith("*")) 
			return title+" "+version+" ["+buildTime+"]";
		return title+" "+version;
	}
}