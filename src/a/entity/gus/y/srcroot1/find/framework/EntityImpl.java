package a.entity.gus.y.srcroot1.find.framework;

import java.io.File;

import a.framework.Entity;
import a.framework.G;
import a.framework.Outside;
import a.framework.Service;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231201";}

	private Service find;
	private File dir;

	public EntityImpl() throws Exception {
		find = Outside.service(this, "gus.y.srcroot1");
		dir = new File((File) find.g(), "a/framework");
	}

	public Object g() throws Exception {
		return dir;
	}
}
