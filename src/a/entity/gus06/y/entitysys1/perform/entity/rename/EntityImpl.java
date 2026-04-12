package a.entity.gus06.y.entitysys1.perform.entity.rename;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import a.framework.*;

public class EntityImpl implements Entity, F, P {

	public String creationDate() {return "20251113";}

	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findClassFiles;
	private Service validate;
	private Service read;
	private Service refactorLinks;

	public EntityImpl() throws Exception
	{
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		findClassFiles = Outside.service(this, "gus.x.dir.listing0.files.class1");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		read = Outside.service(this, "gus.x.file.string.read");
		refactorLinks = Outside.service(this, "gus.y.entitysys1.perform.refactor.downlinks");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String name0 = (String) o[1];
		String name1 = (String) o[2];
		boolean refactor = (boolean) o[3];

		File srcDir = (File) ((R) engine).r("srcDir");
		String devId = (String) ((R) engine).r("devId");

		if (devId != null && !name0.startsWith(devId + ".")) return false;

		if (devId != null && !name1.startsWith(devId + ".")) name1 = devId + "." + name1;

		if (!validate.f(name1)) return false;

		File packageDir0 = (File) findPackageDir.t(new Object[] { srcDir, name0 });
		File packageDir1 = (File) findPackageDir.t(new Object[] { srcDir, name1 });

		if (new File(packageDir1, "EntityImpl.java").exists()) return false;

		File[] javaFiles0 = (File[]) findJavaFiles.t(packageDir0);

		packageDir1.mkdirs();

		for (File javaFile0 : javaFiles0)
		{
			File javaFile1 = new File(packageDir1, javaFile0.getName());
			transfer(javaFile0, javaFile1, name0, name1);
		}

		for (File javaFile0 : javaFiles0) 
		Files.deleteIfExists(javaFile0.toPath());
		
		cleanDir(packageDir0);
		
		// clean entity package inside bin for name0
		
		File binDir = new File(srcDir.getParentFile(), "bin");
		File binPackageDir = (File) findPackageDir.t(new Object[] { binDir, name0 });
		if (binPackageDir.isDirectory())
		{
			File[] classFiles = (File[]) findClassFiles.t(binPackageDir);
			if (classFiles != null) for (File classFile : classFiles) 
			Files.deleteIfExists(classFile.toPath());
				
			cleanDir(binPackageDir);
		}

		if (refactor) refactorLinks.p(new Object[] { engine, name0, name1 });
		((V) engine).v("entityRenamed", new String[] { name0, name1 });

		return true;
	}

	private void transfer(File f0, File f1, String name0, String name1) throws Exception
	{
		String src = (String) read.t(f0);

		src = src.replace("package a.entity." + name0 + ";", "package a.entity." + name1 + ";");

		PrintStream p = new PrintStream(f1);
		p.print(src);
		p.close();
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