package a.entity.gus06.dir.perform.rename.ask;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20170303";}


	private Service input;
	private Service format;


	public EntityImpl() throws Exception
	{
		input = Outside.service(this,"gus06.input.text.dialog");
		format = Outside.service(this,"gus06.string.transform.format.pathinput1");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		
		String oldName = dir.getName();
		String newName = (String) input.t(new String[]{"Enter new name:",oldName});
		
		if(newName==null) return null;
		if(newName.equals("")) return null;
		
		newName = format(newName);
		if(newName.equals(oldName)) return null;
		
		File dir1 = new File(dir.getParentFile(),newName);
		dir1.getParentFile().mkdirs();
		
		rename(dir,dir1);
		return dir1;
	}
	
	
	
	
	private void rename(File dir0, File dir1) throws Exception
	{
		boolean r = dir0.renameTo(dir1);
		if(!r) throw new Exception("Failed to rename dir: "+dir0+" to dir "+dir1);
	}
	
	
	
	private String format(String s) throws Exception
	{return (String) format.t(s);}
}
