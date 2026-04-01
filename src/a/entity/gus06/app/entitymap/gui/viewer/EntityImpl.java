package a.entity.gus06.app.entitymap.gui.viewer;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140804";}

	private Map entitymap;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
		entitymap = (Map) Outside.resource(this,"entitymap");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		viewer.p(entitymap);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
}
