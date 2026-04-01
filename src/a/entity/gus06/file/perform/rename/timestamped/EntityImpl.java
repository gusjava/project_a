package a.entity.gus06.file.perform.rename.timestamped;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20151004";}


	private Service updateName;


	public EntityImpl() throws Exception
	{
		updateName = Outside.service(this,"gus06.string.transform.timestamp.update.head");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		
		String oldName = file.getName();
		String newName = (String) updateName.t(oldName);
		
		if(newName.equals(oldName)) return null;
		
		File file1 = new File(file.getParentFile(),newName);
		rename(file,file1);
		return file1;
	}
	
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
}
