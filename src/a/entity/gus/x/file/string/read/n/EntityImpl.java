package a.entity.gus.x.file.string.read.n;

import java.io.File;
import java.io.FileReader;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240712";}
	
	public Object t(Object obj) throws Exception {
		File file = (File) obj;
		FileReader fr = new FileReader(file);
		char[] a = new char[(int) file.length()];
		fr.read(a, 0, (int) file.length());
		fr.close();
		return new String(a).replace(System.lineSeparator(), "\n");
	}
}
