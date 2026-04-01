package a.entity.gus06.dir.runtask.org.flatten.withname;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150618";}


	private Service listing;
	private Service removeDir;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		removeDir = Outside.service(this,"gus06.dir.perform.remove");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		int length = dir.getAbsolutePath().length();
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			
			String relPath = f.getAbsolutePath().substring(length);
			String newName = relPath.replace(File.separator,"_");
			if(newName.startsWith("_")) newName = newName.substring(1);
			
			File f1 = new File(dir,newName);
			rename(f,f1);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
		
		File[] dd = dir.listFiles();
		for(File d:dd) if(d.isDirectory())
		removeDir.p(d);
	}
	
	
	
	private void rename(File f, File f1) throws Exception
	{
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename path: "+f+" into path: "+f1);
	}
}
