package a.entity.gus06.app.main.rb.gui.viewer;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140925";}

	private Map map;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
		map = (Map) Outside.resource(this,"call.g#gus.app.main.rb");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		viewer.p(map);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
}
