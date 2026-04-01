package a.entity.gus06.file.perform.rename.inferredext;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, F, T {

	public String creationDate() {return "20180410";}


	private Service inferExt;
	private Service autoRename;
	
	public EntityImpl() throws Exception
	{
		inferExt = Outside.service(this,"gus06.file.ext.infer");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	public boolean f(Object obj) throws Exception
	{return t(obj)!=null;}
	
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		String ext = (String) inferExt.t(obj);
		
		if(file.getName().endsWith("."+ext)) return null;
		
		File file1 = new File(file.getAbsolutePath()+"."+ext);
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
