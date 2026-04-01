package a.entity.gus06.dir.perform.create.ask;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20140917";}


	private Service input;
	private Service format;
	private Service copyFromClipboard;
	private Service moveFromClipboard;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		format = Outside.service(this,"gus06.string.transform.format.pathinput1");
		copyFromClipboard = Outside.service(this,"gus06.dir.perform.copyfiles.fromclipboard");
		moveFromClipboard = Outside.service(this,"gus06.dir.perform.movefiles.fromclipboard");
	}
	
	
	
	public void p(Object obj) throws Exception
	{f(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		if(!dir.exists())
		{
			dir.mkdirs();
			return dir;
		}
		
		String name = askForName(dir);
		if(name==null || name.equals("")) return null;
		
		if(!name.contains(";"))
		return create(dir,name);
		
		String[] n = name.split(";");
		File[] d = new File[n.length];
		for(int i=0;i<d.length;i++) d[i] = create(dir,n[i]);
		
		return d;
	}
	
	
	
	private String askForName(File file) throws Exception
	{
		String initValue = file.isFile()?file.getName():"";
		String message = "Please, enter directory's name:";
		return (String) input.t(new String[]{message,initValue});
	}
	
	private File getDir(File file)
	{
		if(file.isDirectory()) return file;
		return file.getParentFile();
	}
	
	
	private File create(File dir, String name) throws Exception
	{
		boolean copy = name.endsWith("*");
		if(copy) name = name.substring(0,name.length()-1);
		
		boolean move = name.endsWith("*");
		if(move) name = name.substring(0,name.length()-1);
		
		File dir1 = new File(dir,format(name));
		dir1.mkdirs();
		
		if(move) moveFromClipboard.p(dir1);
		else if(copy) copyFromClipboard.p(dir1);
		
		return dir1;
	}
	
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
