package a.entity.gus06.appli.gusclient1.project.config.load2.path;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150312";}


	private Service loadProp;
	private Service buildPathMap;


	public EntityImpl() throws Exception
	{
		loadProp = Outside.service(this,"gus06.appli.gusclient1.project.config.load2.prop");
		buildPathMap = Outside.service(this,"gus06.app.path.build.fromprop");
	}
	
	
	public Object g() throws Exception
	{
		Map prop = (Map) loadProp.g();
		return buildPathMap.t(prop);
	}
}
