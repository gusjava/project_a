package a.entity.gus.y.entityeditor1.perform.file.duplicate;

import java.io.File;
import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240113";}

	public static final String MAIN_NAME = "EntityImpl";

	private Service logger;
	private Service read;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		read = Outside.service(this, "gus.x.file.string.read");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4)
			throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		String oldName = (String) o[2];
		String newName = (String) o[3];

		if (oldName.equals(MAIN_NAME))
			return false;
		if (newName.equals(MAIN_NAME))
			return false;

		File rootDir = (File) ((R) engine).r("rootDir");
		String entityPackage = "a.entity." + entityName;

		File packageDir = new File(rootDir, entityPackage.replace(".", File.separator));
		if (!packageDir.isDirectory())
			return false;

		String oldFileName = oldName + ".java";
		File oldFile = new File(packageDir, oldFileName);
		if (!oldFile.exists())
			return false;

		String newFileName = newName + ".java";
		File newFile = new File(packageDir, newFileName);
		if (newFile.exists())
			return false;

		log("Duplicating java file " + oldFile + " into " + newFile);
		
		transfer(oldFile, newFile, oldName, newName);

		((V) engine).v("fileDuplicated", new File[] {oldFile, newFile});
		return true;
	}

	private void transfer(File f0, File f1, String name0, String name1) throws Exception {
		String src = (String) read.t(f0);

		// TODO largement insuffisant ...
		// Il faudrait identifier le type name0 dans la structure java
		src = src.replace(name0, name1);

		PrintStream p = new PrintStream(f1);
		p.print(src);
		p.close();
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception {
		logger.p(new Object[] {this, msg});
	}
}
