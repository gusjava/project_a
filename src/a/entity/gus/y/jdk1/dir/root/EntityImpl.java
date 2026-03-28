package a.entity.gus.y.jdk1.dir.root;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, G {
	public String creationDate() {return "20240112";}

	private Service home;
	private File dir;

	public EntityImpl() throws Exception {
		home = Outside.service(this, "gus.x.jre.prop.javahome.as.file");
	}

	public Object g() throws Exception {
		if (dir == null)
			init();
		return dir;
	}

	private void init() throws Exception {
		File homeDir = (File) home.g();

		dir = homeDir.getParentFile();
		while (dir != null && !isJavaRoot(dir))
			dir = dir.getParentFile();
		if (dir == null)
			throw new Exception("Java root directory not found from homeDir: " + homeDir);
	}

	private boolean isJavaRoot(File dir) {
		return dir.getName().toLowerCase().equals("java");
	}
}
