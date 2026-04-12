package a.entity.gus06.y.entitysys1.perform.entity.delete;

import java.io.File;
import java.nio.file.Files;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251113";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findClassFiles;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		findClassFiles = Outside.service(this, "gus.x.dir.listing0.files.class1");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];

		File srcDir = (File) ((R) engine).r("srcDir");
		String devId = (String) ((R) engine).r("devId");

		if (devId != null && !entityName.startsWith(devId + ".")) return false;

		File packageDir = (File) findPackageDir.t(new Object[] { srcDir, entityName });
		if (!packageDir.isDirectory()) return false;

		File[] javaFiles = (File[]) findJavaFiles.t(packageDir);
		if (javaFiles == null || javaFiles.length == 0) return false;

		for (File javaFile : javaFiles) 
		Files.deleteIfExists(javaFile.toPath());
		
		cleanDir(packageDir);
		
		// clean entity package inside bin
		
		File binDir = new File(srcDir.getParentFile(), "bin");
		File binPackageDir = (File) findPackageDir.t(new Object[] { binDir, entityName });
		if (binPackageDir.isDirectory())
		{
			File[] classFiles = (File[]) findClassFiles.t(binPackageDir);
			if (classFiles != null) for (File classFile : classFiles) 
			Files.deleteIfExists(classFile.toPath());
			
			cleanDir(binPackageDir);
		}
		
		((V) engine).v("entityDeleted", entityName);
		return true;
	}

	private void cleanDir(File dir) throws Exception
	{
		while (dir != null && dir.isDirectory() && dir.list() != null && dir.list().length == 0)
		{
			Files.deleteIfExists(dir.toPath());
			dir = dir.getParentFile();
		}
	}
}
