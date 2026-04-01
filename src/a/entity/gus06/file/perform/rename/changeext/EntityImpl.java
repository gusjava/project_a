package a.entity.gus06.file.perform.rename.changeext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20150619";}


	private Service changeExt;
	private Service autoRename;
	
	public EntityImpl() throws Exception
	{
		changeExt = Outside.service(this,"gus06.file.newfile.changeext");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		File file1 = (File) changeExt.t(obj);
		
		file1 = (File) autoRename.t(file1);
		rename(file,file1);
		return file1;
	}
	
	
	
	private void rename(File file0, File file1) throws Exception
	{
		boolean r = file0.renameTo(file1);
		if(!r) throw new Exception("Failed to rename file: "+file0+" to file "+file1);
	}
}
