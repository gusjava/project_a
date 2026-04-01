package a.entity.gus06.file.perform.rename.name0.remove1.end;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P, T {

	public String creationDate() {return "20250207";}


	private Service name0ext;
	private Service autoRename;
	
	public EntityImpl() throws Exception
	{
		name0ext = Outside.service(this,"gus06.file.getname0ext");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}
	
	
	
	public void p(Object obj) throws Exception
	{t(obj);}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File file = (File) o[0];
		String seq = (String) o[1];
		
		String[] infos = (String[]) name0ext.t(file);
		String name0 = infos[0];
		String ext = infos[1];
		
		if(!name0.endsWith(seq)) return file;
		
		String newName0 = name0.substring(0, name0.length()-seq.length());
		String newName = newName0+"."+ext;
		
		if(file.getName().equals(newName)) return file;
		
		File file1 = new File(file.getParentFile(),newName);
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