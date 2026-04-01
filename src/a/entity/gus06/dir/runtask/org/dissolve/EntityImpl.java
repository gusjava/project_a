package a.entity.gus06.dir.runtask.org.dissolve;

import a.framework.*;
import java.io.File;
import java.util.Set;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150602";}
	
	
	
	private Service removeDir;
	
	public EntityImpl() throws Exception
	{
		removeDir = Outside.service(this,"gus06.dir.perform.remove");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File root = dir.getParentFile();
		if(root==null) return;
		
		File oldDir = new File(dir.getAbsolutePath()+"__(to_be_removed)");
		rename(dir,oldDir);
		
		File[] ff = oldDir.listFiles();
		if(progress!=null) ((V)progress).v("size",""+ff.length);
		
		for(File f:ff)
		{
			movePath(f,root);
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		removeDir.p(oldDir);
	}
	
	
	
	private void movePath(File path, File root) throws Exception
	{
		String name = path.getName();
		File newPath = new File(root,name);
		int index = 0;
		while(newPath.exists())
		{
			index++;
			newPath = new File(root,name+"_"+index);
		}
		rename(path,newPath);
	}
	
	
	
	
	
	
	private void rename(File f, File f1) throws Exception
	{
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename path: "+f+" into path: "+f1);
	}
}
