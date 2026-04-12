package a.entity.gus.y.entityeditor1.perform.file.delete;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240113";}

	public static final String MAIN_NAME = "EntityImpl";
	
	private Service logger;
	
	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		String className = (String) o[2];

		if (className.equals(MAIN_NAME))
			return false;

		File rootDir = (File) ((R) engine).r("rootDir");
		String entityPackage = "a.entity." + entityName;

		File packageDir = new File(rootDir, entityPackage.replace(".", File.separator));
		if (!packageDir.isDirectory())
			return false;

		String fileName = className + ".java";
		File javaFile = new File(packageDir, fileName);
		if (!javaFile.exists())
			return false;

		log("Deleting java file " + javaFile);
		Files.deleteIfExists(javaFile.toPath());
		
		File mainFile = new File(javaFile.getParentFile(), MAIN_NAME + ".java");
		Files.setLastModifiedTime(mainFile.toPath(), FileTime.from(Instant.now()));

		((V) engine).v("fileDeleted", javaFile);
		return true;
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception {
		logger.p(new Object[] {this, msg});
	}
}
