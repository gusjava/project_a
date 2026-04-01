package a.entity.gus06.app.jarmap.gui.viewer;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140808";}

	private Map map;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
		map = (Map) Outside.resource(this,"jarmap");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		viewer.p(map);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
}
