package a.entity.gus06.file.op.rename0.autorename;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160612";}


	private Service getName0Ext;
	private Service autoRename;
	
	
	public EntityImpl() throws Exception
	{
		getName0Ext = Outside.service(this,"gus06.file.getname0ext");
		autoRename = Outside.service(this,"gus06.file.newfile.autorename2");
	}

	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File f = (File) o[0];
		String newName = (String) o[1];
		
		if(!f.isFile()) throw new Exception("Invalid input file: "+f);
		
		String[] n = (String[]) getName0Ext.t(f);
		String name = n[0];
		String ext = n[1];
		
		if(name.equals(newName)) return;
		
		File parent = f.getParentFile();
		File f1 = new File(parent,newName+"."+ext);
		
		f1 = (File) autoRename.t(f1);
		
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename file: "+f);
	}
}
