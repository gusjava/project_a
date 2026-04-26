package a.entity.gus.y.entitysys1.perform.entity.delete;

import java.io.File;
import java.nio.file.Files;

import a.framework.*;

public class EntityImpl implements Entity, P, F, T {
	public String creationDate() {return "20240116";}

	private Service logger;
	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findClassFiles;
	private Service hasRights;

	public EntityImpl() throws Exception
	{
		logger = Outside.service(this, "logger");
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		findClassFiles = Outside.service(this, "gus.x.dir.listing0.files.class1");
		hasRights = Outside.service(this,"gus.x.entity.hasrights");
	}

	public void p(Object obj) throws Exception
	{f(obj);}

	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String entityName = (String) o[1];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");
		
		if(!hasRights.f(new Object[]{devId, entityName}))
			return "Dev "+devId+" is not allowed to delete entity "+entityName;

		File packageDir = (File) findPackageDir.t(new Object[] { rootDir, entityName });
		if (!packageDir.isDirectory())
			return "Package dir already exists: "+packageDir;

		File[] javaFiles = (File[]) findJavaFiles.t(packageDir);
		if (javaFiles == null || javaFiles.length == 0)
			return "Not java file found inside package dir: "+packageDir;

		log("Deleting entity " + entityName);

		for (File javaFile : javaFiles) 
		Files.deleteIfExists(javaFile.toPath());
		
		cleanDir(packageDir);
		
		// clean entity package inside bin
		
		File binDir = new File(rootDir.getParentFile(), "bin");
		File binPackageDir = (File) findPackageDir.t(new Object[] { binDir, entityName });
		
		if (binPackageDir.isDirectory())
		{
			File[] classFiles = (File[]) findClassFiles.t(binPackageDir);
			if (classFiles != null)
				for (File classFile : classFiles) 
				Files.deleteIfExists(classFile.toPath());
			cleanDir(binPackageDir);
		}
		
		((V) engine).v("entityDeleted", entityName);
		return null;
	}

	private void cleanDir(File dir) throws Exception
	{
		while (dir != null && dir.isDirectory() && dir.list() != null && dir.list().length == 0)
		{
			Files.deleteIfExists(dir.toPath());
			dir = dir.getParentFile();
		}
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception
	{logger.p(new Object[] {this, msg});}
}
