package a.entity.gus.x.file.getext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20231128";}

	public Object t(Object obj) throws Exception {
		if (obj instanceof File)
			return ext((File) obj);
		if (obj instanceof String)
			return ext((String) obj);
		throw new Exception("Invalid data type: " + obj.getClass().getName());
	}

	private String ext(File file) {
		String name = file.getName();
		return ext(name);
	}

	private String ext(String name) {
		if (!name.contains("."))
			return "";

		String[] n = name.split("\\.");
		return n[n.length - 1];
	}
}