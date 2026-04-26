package a.entity.gus.y.entitysys1.perform.entity.duplicate;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, P, F, T {
	public String creationDate() {return "20240116";}
	
	public static final String ENTITY_FILENAME = "EntityImpl.java";

	private Service logger;
	private Service findPackageDir;
	private Service findJavaFiles;
	private Service validate;
	private Service readFile;
	private Service writeFile;
	private Service completeName;
	private Service updateCreationDate;

	public EntityImpl() throws Exception
	{
		updateCreationDate = Outside.service(this,"gus.x.entity.src.creationdate.updatenow");
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		completeName = Outside.service(this,"gus.x.entity.completename");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		readFile = Outside.service(this, "gus.x.entity.src.read1");
		writeFile = Outside.service(this, "gus.x.entity.src.write1");
		logger = Outside.service(this, "logger");
	}

	public void p(Object obj) throws Exception
	{t(obj);}

	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
		
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String name0 = (String) o[1];
		String name1 = (String) o[2];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");

		name1 = (String) completeName.t(new Object[]{devId, name1});
		if (!validate.f(name1)) return "Invalid entity name: "+name1;

		File packageDir0 = (File) findPackageDir.t(new Object[] { rootDir, name0 });
		File packageDir1 = (File) findPackageDir.t(new Object[] { rootDir, name1 });
		File entityFile1 = new File(packageDir1, ENTITY_FILENAME);

		if (entityFile1.exists()) return entityFile1+" already exists";

		File[] javaFiles0 = (File[]) findJavaFiles.t(packageDir0);

		log("Duplicating entity " + name0 + " into " + name1);

		packageDir1.mkdirs();

		for (File javaFile0 : javaFiles0)
		{
			File javaFile1 = new File(packageDir1, javaFile0.getName());
			transfer(javaFile0, javaFile1, name0, name1);
		}

		((V) engine).v("entityDuplicated", new String[] { name0, name1 });
		return null;
	}

	private void transfer(File f0, File f1, String name0, String name1) throws Exception
	{
		String src = (String) readFile.t(f0);

		src = src.replace("package a.entity." + name0 + ";", "package a.entity." + name1 + ";");
		src = (String) updateCreationDate.t(src);
		
		writeFile.p(new Object[]{f1,src});
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception
	{logger.p(new Object[] {this, msg});}
}
