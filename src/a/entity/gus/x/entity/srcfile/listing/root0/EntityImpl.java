package a.entity.gus.x.entity.srcfile.listing.root0;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231203";}

	public static final String FILENAME = "EntityImpl.java";

	public Object t(Object obj) throws Exception {
		File rootDir = (File) obj;
		List list = new ArrayList();

		File entityDir = new File(new File(rootDir, "a"), "entity");
		if (!entityDir.isDirectory())
			return list;

		int rootLength = entityDir.getAbsolutePath().length();
		scan(entityDir, rootLength, list);

		Collections.sort(list);
		return list;
	}

	private void scan(File path, int rootLength, List list) {
		if (isEntityFile(path))
			list.add(toEntityName(path, rootLength));

		else if (path.isDirectory()) {
			File[] ff = path.listFiles();
			for (File f : ff)
				scan(f, rootLength, list);
		}
	}

	private boolean isEntityFile(File f) {
		return f.isFile() && f.getName().equals(FILENAME);
	}

	private String toEntityName(File f, int rootLength) {
		String p = f.getParent();
		return p.substring(rootLength + 1).replace(File.separator, ".");
	}
}
