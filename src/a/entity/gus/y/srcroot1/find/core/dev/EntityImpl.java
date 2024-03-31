package a.entity.gus.y.srcroot1.find.core.dev;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20231203";}

	private Service findDev;
	private Service findRoot;

	public EntityImpl() throws Exception {
		findDev = Outside.service(this, "gus.y.srcroot1.dev");
		findRoot = Outside.service(this, "gus.y.srcroot1.find.core");
	}

	public Object g() throws Exception {
		File rootDir = (File) findRoot.g();
		String devId = (String) findDev.g();
		
		if (devId == null || rootDir == null)
			return null;
		
		return new File(rootDir, devId);
	}
}
