package a.entity.gus06.app.prop.gui.viewer;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20141022";}

	private Map prop;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
		prop = (Map) Outside.resource(this,"prop");
		viewer = Outside.service(this,"*gus06.data.viewer.map.stringmap");
		viewer.p(prop);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
}
