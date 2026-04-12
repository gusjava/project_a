package a.entity.gus.y.entityimporter1.engine1;

import java.io.File;

import a.framework.Entity;
import a.framework.Outside;
import a.framework.P;
import a.framework.Service;

public class EntityImpl implements Entity, P {
	public String creationDate() {return "20210814";}

	private Service engine;
	private Service getRootDir;

	public EntityImpl() throws Exception {
		engine = Outside.service(this, "gus.y.entityimporter1.engine2");
		getRootDir = Outside.service(this, "gus.y.srcroot1");
	}

	public void p(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		
		File rootDir = (File) getRootDir.g();
		if(rootDir==null || !rootDir.isDirectory()) return;

		File inputRoot = (File) o[0];
		String input = (String) o[1];

		String[] lines = input.split("\n");

		String line0 = null;
		for (String line : lines)
			if (!line.trim().equals("")) {
				if (line0 == null)
					line0 = line;
				else {
					engine.p(new Object[] { inputRoot, line0, rootDir, line });
					line0 = null;
				}
			}
	}
}
