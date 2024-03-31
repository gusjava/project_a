package a.entity.gus.y.entitysys1.perform.entity.duplicate;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20240116";}
	
	public static final String ENTITY_FILENAME = "EntityImpl.java";

	private Service logger;
	private Service findPackageDir;
	private Service findJavaFiles;
	private Service validate;
	private Service read;

	public EntityImpl() throws Exception {
		logger = Outside.service(this, "logger");
		findPackageDir = Outside.service(this, "gus.x.entity.src.find.packagedir");
		findJavaFiles = Outside.service(this, "gus.x.dir.listing0.files.java");
		validate = Outside.service(this, "gus.x.entity.name.validate");
		read = Outside.service(this, "gus.x.file.string.read");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 3)
			throw new Exception("Wrong data number: " + o.length);

		Object engine = o[0];
		String name0 = (String) o[1];
		String name1 = (String) o[2];

		File rootDir = (File) ((R) engine).r("rootDir");
		String devId = (String) ((R) engine).r("devId");

		if (devId != null && !name1.startsWith(devId + "."))
			name1 = devId + "." + name1;

		if (!validate.f(name1))
			return false;

		File packageDir0 = (File) findPackageDir.t(new Object[] { rootDir, name0 });
		File packageDir1 = (File) findPackageDir.t(new Object[] { rootDir, name1 });

		if (new File(packageDir1, ENTITY_FILENAME).exists())
			return false;

		File[] javaFiles0 = (File[]) findJavaFiles.t(packageDir0);

		log("Duplicating entity " + name0 + " into " + name1);

		packageDir1.mkdirs();

		for (File javaFile0 : javaFiles0) {
			File javaFile1 = new File(packageDir1, javaFile0.getName());
			transfer(javaFile0, javaFile1, name0, name1);
		}

		((V) engine).v("entityDuplicated", new String[] { name0, name1 });
		return true;
	}

	private void transfer(File f0, File f1, String name0, String name1) throws Exception {
		String src = (String) read.t(f0);

		src = src.replace("package a.entity." + name0 + ";", "package a.entity." + name1 + ";");

		String creationDateRegex = "public String creationDate\\(\\) \\{return \"([0-9]{8})\";\\}";
		String creationDateCurrent = "public String creationDate() {return \"" + today() + "\";}";

		src = src.replaceFirst(creationDateRegex, creationDateCurrent);

		PrintStream p = new PrintStream(f1);
		p.print(src);
		p.close();
	}

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	private String today() {
		return sdf.format(new Date());
	}
	
	/*
	 * LOGGER
	 */
	
	private void log(String msg) throws Exception {
		logger.p(new Object[] {this, msg});
	}
}
