package a.entity.gus.y.dataview1.map.main;

import java.util.Map;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20231129";}

	private Service viewer;
	private Map main;

	public EntityImpl() throws Exception {
		viewer = Outside.service(this, "*gus.y.dataview1.map");
		main = (Map) Outside.resource(this, "main");
		viewer.p(main);
	}

	public Object i() throws Exception {
		return viewer.i();
	}
}
