package a.entity.gus.y.entitysys1.perform.entity.replaceall;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, P, F, T {
	public String creationDate() {return "20260426";}
	
	public static final String ENTITY_FILENAME = "EntityImpl.java";

	private Service logger;
	private Service findPackageDir;
	private Service findJavaFiles;
	private Service findClassFiles;
	private Service validate;
	private Service read;
	private Service refactorLinks;
	private Service hasRights;

	public EntityImpl() throws Exception
	{
		logger = Outside.service(this, "logger");
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		findClassFiles = Outside.service(this, "gus.x.dir.listing0.files.class1");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		read = Outside.service(this, "gus.x.file.string.read.v2");
		refactorLinks = Outside.service(this, "gus.y.entitysys1.perform.refactor.downlinks");
		hasRights = Outside.service(this,"gus.x.entity.hasrights");
	}

	public void p(Object obj) throws Exception
	{t(obj);}

	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 2) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		List names = (List) o[1];
		
		if(names.size()<2) throw new Exception("Invalid names size: "+names.size());
		
		String name1 = (String) names.get(names.size()-1);

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");
		
		File packageDir1 = (File) findPackageDir.t(new Object[] { rootDir, name1 });
		File entityFile1 = new File(packageDir1, ENTITY_FILENAME);
		if (!entityFile1.exists()) return entityFile1+" does not exist";
		
		for(int i=0;i<names.size()-1;i++)
		{
			String name0 = (String) names.get(i);
			if(!hasRights.f(new Object[]{devId, name0})) return false;
			File packageDir0 = (File) findPackageDir.t(new Object[] { rootDir, name0 });
			File entityFile0 = new File(packageDir0, ENTITY_FILENAME);
			if (!entityFile0.exists()) return entityFile0+" does not exist";
		}
		
		for(int i=0;i<names.size()-1;i++)
		{
			String name0 = (String) names.get(i);
			File packageDir0 = (File) findPackageDir.t(new Object[] { rootDir, name0 });
			
			refactorLinks.p(new Object[] { engine, name0, name1 });
			File[] javaFiles0 = (File[]) findJavaFiles.t(packageDir0);
			log("Replacing entity " + name0 + " by " + name1);
			
			for (File javaFile0 : javaFiles0)
				Files.deleteIfExists(javaFile0.toPath());
				
			cleanDir(packageDir0);
			
			// clean entity package inside bin for name0
			
			File binDir = new File(rootDir.getParentFile(), "bin");
			File binPackageDir = (File) findPackageDir.t(new Object[] { binDir, name0 });
			if (binPackageDir.isDirectory())
			{
				File[] classFiles = (File[]) findClassFiles.t(binPackageDir);
				if (classFiles != null)
					for (File classFile : classFiles) 
					Files.deleteIfExists(classFile.toPath());
				cleanDir(binPackageDir);
			}
		}
		
		((V) engine).v("entitiesReplaced", names);
		return null;
	}

	private void cleanDir(File dir) throws Exception
	{
		while (dir != null && dir.isDirectory() && dir.list() != null && dir.list().length == 0) {
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
