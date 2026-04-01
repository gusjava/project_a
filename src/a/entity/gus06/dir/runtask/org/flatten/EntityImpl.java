package a.entity.gus06.dir.runtask.org.flatten;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150602";}


	private Service listing;
	private Service cleanDir;
	private Service move;
	
	
	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing.dirtofiles");
		cleanDir = Outside.service(this,"gus06.dir.perform.clean");
		move = Outside.service(this,"gus06.file.op.move.autorename");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		File[] dd = dir.listFiles();
		for(File d:dd) if(d.isDirectory())
		{
			File d1 = new File(d.getAbsolutePath()+"__(to_be_removed)");
			rename(d,d1);
		}
		
		List l = (List) listing.t(dir);
		if(progress!=null) ((V)progress).v("size",""+l.size());
		
		for(int i=0;i<l.size();i++)
		{
			File f = (File) l.get(i);
			if(!f.getParentFile().equals(dir))
			movePath(f,dir);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) return;
		}
		
		dd = dir.listFiles();
		for(File d:dd) if(d.isDirectory())
		cleanDir.p(d);
	}
	
	
	
	
	private void movePath(File f, File root) throws Exception
	{
		String name = f.getName();
		File f2 = new File(root, name);
		move.p(new File[]{f,f2});
	}
	
	
	
	
	private void rename(File f, File f1) throws Exception
	{
		boolean r = f.renameTo(f1);
		if(!r) throw new Exception("Failed to rename path: "+f+" into path: "+f1);
	}
}
