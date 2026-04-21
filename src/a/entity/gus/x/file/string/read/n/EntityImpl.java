package a.entity.gus.x.file.string.read.n;

import java.io.File;
import java.nio.file.Files;

import a.framework.Entity;
import a.framework.T;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240712";}
	
	public Object t(Object obj) throws Exception {
		File file = (File) obj;
		return Files.readString(file.toPath()).replace(System.lineSeparator(), "\n");
	}
}
