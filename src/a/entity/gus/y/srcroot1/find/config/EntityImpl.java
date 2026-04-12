package a.entity.gus.y.srcroot1.find.config;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231203";}

	private Service find;

	public EntityImpl() throws Exception {
		find = Outside.service(this, "gus.y.srcroot1");
	}
	
	public Object g() throws Exception {
		File root = (File) find.g();
		return root!=null ? new File(root, "a/config") : null;
	}
}
