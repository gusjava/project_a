package a.entity.gus06.appli.gusclient1.template.manager;

import a.framework.*;
import java.io.File;
import java.io.FileFilter;
import java.io.PrintStream;
import java.util.ArrayList;


public class EntityImpl extends S1 implements Entity, R, V, G {

	public String creationDate() {return "20140905";}

	public static final FileFilter FILTER = new FileFilter() {
		public boolean accept(File f) {return f.isFile();}
	};

	private Service read;
	private File dir;
	
	

	public EntityImpl() throws Exception
	{
		read = Outside.service(this,"gus06.file.read.string");
		dir = (File) Outside.resource(this,"path#path.templatedir");
		dir.mkdirs();
	}
	
	
	public Object g() throws Exception
	{
		File[] f = dir.listFiles(FILTER);
		ArrayList ids = new ArrayList();
		for(int i=0;i<f.length;i++) ids.add(f[i].getName());
		return ids;
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.startsWith("file."))
			return idToFile(key.substring(5));
			
		File file = idToFile(key);
		if(!file.exists()) return null;
		return read.t(file);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		String template = (String) obj;
		File file = idToFile(key);
		
		if(template==null) file.delete();
		else write(file,template);
		modified();
	}
	
	
	
	
	private void write(File file, String s) throws Exception
	{
		PrintStream p = new PrintStream(file);
		p.print(s);
		p.close();
	}
	
	
	
	
	private File idToFile(String id)
	{return new File(dir,id);}
	
	
	private void modified()
	{send(this,"modified()");}
}
