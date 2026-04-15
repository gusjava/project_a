package a.entity.gus.y.entitysrcedit1.replace;

import java.io.File;
import java.io.PrintStream;

import a.framework.*;

public class EntityImpl implements Entity, P, F {
	public String creationDate() {return "20260414";}
	
	public static final String CLASS_NAME = "EntityImpl";

	private Service read;

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus.x.entity.src.read1");
	}

	public void p(Object obj) throws Exception {
		f(obj);
	}

	public boolean f(Object obj) throws Exception {
		Object[] o = (Object[]) obj;
		if (o.length != 4) throw new Exception("Wrong data number: " + o.length);

		File rootDir = (File) o[0];
		String entityName = (String) o[1];
		int[] range = (int[]) o[2];
		String replacement = (String) o[3];
		
		if (range.length != 2) throw new Exception("Wrong range size: " + range.length);
		int start = range[0];
		int end = range[1];

		String entityPackage = "a.entity." + entityName;
		File packageDir = new File(rootDir, entityPackage.replace(".", File.separator));

		File javaFile = new File(packageDir, CLASS_NAME + ".java");
		if (!javaFile.exists()) return false;
		
		String src = (String) read.t(javaFile);
		String part1 = src.substring(0, Math.min(start, src.length()));
		String part2 = end < src.length() ? src.substring(end) : "";

		PrintStream p = new PrintStream(javaFile);

		p.print(part1);
		p.print(replacement);
		p.print(part2);

		return true;
	}
}
