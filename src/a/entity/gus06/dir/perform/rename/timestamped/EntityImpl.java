package a.entity.gus06.dir.perform.rename.timestamped;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20170303";}


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
		File dir = (File) obj;
		
		String oldName = dir.getName();
		String newName = (String) updateName.t(oldName);
		
		if(newName.equals(oldName)) return null;
		
		File dir1 = new File(dir.getParentFile(),newName);
		rename(dir,dir1);
		return dir1;
	}
	
	
	
	
	private void rename(File dir0, File dir1) throws Exception
	{
		boolean r = dir0.renameTo(dir1);
		if(!r) throw new Exception("Failed to rename dir: "+dir0+" to dir "+dir1);
	}
}
