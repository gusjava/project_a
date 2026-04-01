package a.entity.gus06.command.updateapp;

import java.io.File;

import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20140706";}


	public static final String PATH = "path.receiveddir";
	

	private Service replace;
	private Service findRecent;
	private File dir;
	
	
	public EntityImpl() throws Exception
	{
		replace = Outside.service(this,"gus06.app.jarfile.replace");
		findRecent = Outside.service(this,"gus06.dir.children.mostrecent");
		
		dir = (File) Outside.resource(this,"path#"+PATH);
		if(dir==null) throw new Exception("Undefined path: "+PATH);
		dir.mkdirs();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		File file = toFile(obj);
		if(file!=null) updateApp(file);
	}
	
	
	
	public void e() throws Exception
	{
		File file = recent();
		if(file!=null) updateApp(file);
	}
	
	
	
	
	
	
	private void updateApp(File file) throws Exception
	{
		String command = (String) Outside.resource(this,"property#exec.restart");
		if(command==null) throw new Exception("Undefined property: exec.restart");
		
		replace.p(file);
		Runtime.getRuntime().exec(command);
		System.exit(0);
	}
	
	
	
	
	
	
	private File toFile(Object obj) throws Exception
	{
		if(obj instanceof File) return (File) obj;
		if(obj instanceof String) return toFile((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private File toFile(String name) throws Exception
	{
		File file = new File(dir,name);
		if(file.exists()) return file;
		return null;
	}
	
	private File recent() throws Exception
	{
		return (File) findRecent.t(dir);
	}
}
