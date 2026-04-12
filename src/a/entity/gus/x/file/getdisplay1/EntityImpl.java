package a.entity.gus.x.file.getdisplay1;

import java.io.File;
import java.io.IOException;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240111";}

	public Object t(Object obj) throws Exception {
		if (obj==null)
			return "null";
		if (obj instanceof File)
			return display((File) obj);
		if (obj instanceof String)
			return display((String) obj);
		throw new Exception("Invalid data type: " + obj.getClass().getName());
	}

	private String display(String path) throws IOException {
		return display(new File(path));
	}

	private String display(File file) throws IOException {
		String path = file.getCanonicalPath();
		if(file.isDirectory()) return path+" [dir]";
		if(file.isFile()) return path+" [file]";
		return path+" [notFound]";
	}
}