package a.entity.gus06.dir.perform.duplicate.timestamped;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T, F {

	public String creationDate() {return "20151004";}


	private Service updateName;
	private Service copy;


	public EntityImpl() throws Exception
	{
		updateName = Outside.service(this,"gus06.string.transform.timestamp.update.head");
		copy = Outside.service(this,"gus06.dir.op.copy");
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
		if(dir1.exists()) throw new Exception("Target path already exists: "+dir1);
		
		copy.p(new File[]{dir,dir1});
		return dir1;
	}
}
