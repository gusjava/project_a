package a.entity.gus06.y.entityeditor1.perform.file.delete;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251115";}

	public static final String MAIN_NAME = "EntityImpl";

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];
		String className = (String) o[2];

		if (className.equals(MAIN_NAME)) return false;

		File srcDir = (File) ((R) engine).r("srcDir");
		String entityPackage = "gus06.entity." + entityName;

		File packageDir = new File(srcDir, entityPackage.replace(".", File.separator));
		if (!packageDir.isDirectory()) return false;

		String fileName = className + ".java";
		File javaFile = new File(packageDir, fileName);
		if (!javaFile.exists()) return false;

		Files.deleteIfExists(javaFile.toPath());
		
		File mainFile = new File(javaFile.getParentFile(), MAIN_NAME + ".java");
		Files.setLastModifiedTime(mainFile.toPath(), FileTime.from(Instant.now()));

		((V) engine).v("fileDeleted", javaFile);
		return true;
	}
}
