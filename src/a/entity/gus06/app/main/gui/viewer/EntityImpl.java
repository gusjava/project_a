package a.entity.gus06.app.main.gui.viewer;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140731";}

	private Map main;
	private Service viewer;

	
	public EntityImpl() throws Exception
	{
		main = (Map) Outside.resource(this,"main");
		viewer = Outside.service(this,"*gus06.data.viewer.map");
		viewer.p(main);
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
}
