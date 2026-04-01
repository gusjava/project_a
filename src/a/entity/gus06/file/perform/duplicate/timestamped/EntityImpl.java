package a.entity.gus06.file.perform.duplicate.timestamped;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20151004";}


	private Service updateName;
	private Service copy;


	public EntityImpl() throws Exception
	{
		updateName = Outside.service(this,"gus06.string.transform.timestamp.update.head");
		copy = Outside.service(this,"gus06.file.op.copy");
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
		copy.p(new File[]{file,file1});
		return file1;
	}
}
