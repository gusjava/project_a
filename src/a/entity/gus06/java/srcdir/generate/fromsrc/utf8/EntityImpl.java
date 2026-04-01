package a.entity.gus06.java.srcdir.generate.fromsrc.utf8;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160804";}


	private Service findClassPath;
	private Service findFile;


	public EntityImpl() throws Exception
	{
		findClassPath = Outside.service(this,"gus06.java.srccode.extract.classpath");
		findFile = Outside.service(this,"gus06.java.srcdir.retrieve.javafile");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String src = (String) o[0];
		File dir = (File) o[1];
		
		String classPath = (String) findClassPath.t(src);
		if(invalid(classPath)) throw new Exception("Invalid java classpath: "+classPath);
		
		File file = (File) findFile.t(new Object[]{dir,classPath});
		file.getParentFile().mkdirs();
		
		PrintStream p = new PrintStream(file,"UTF-8");
		p.print(src.trim());
		p.close();
	}
	
	
	private boolean invalid(String s)
	{return s.contains("\n") || s.contains("\t") || s.contains(" ");}
}
