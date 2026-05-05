package a.entity.gus06.file.op.rename0;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160611";}


	private Service getName0Ext;
	
	
	public EntityImpl() throws Exception
	{
		getName0Ext = Outside.service(this,"gus.x.file.getname0ext");
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
		
		if(f1.exists()) throw new Exception("Path already exists: "+f1);
		
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename file: "+f);
	}
}
