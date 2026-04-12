package a.entity.gus.y.dirs1.listing0.files.name0;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231202";}

	public static final FileFilter FILEFILTER = new FileFilter() {
		public boolean accept(File f) {
			return f.isFile();
		}
	};

	private Service getName;

	public EntityImpl() throws Exception {
		getName = Outside.service(this, "gus.x.file.getname0");
	}

	public Object t(Object obj) throws Exception {
		File dir = (File) obj;
		File[] f = dir.listFiles(FILEFILTER);

		String[] n = new String[f.length];
		for (int i = 0; i < f.length; i++)
			n[i] = (String) getName.t(f[i]);

		return n;
	}
}