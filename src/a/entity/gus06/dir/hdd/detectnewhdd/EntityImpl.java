package a.entity.gus06.dir.hdd.detectnewhdd;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150711";}
	
	public static final long TIMEOUT = 30000;

	
	
	public Object g() throws Exception
	{
		File[] roots1 = File.listRoots();
		long t1 = System.currentTimeMillis();
		
		while(System.currentTimeMillis()-t1 < TIMEOUT)
		{
			File[] roots2 = File.listRoots();
			if(roots2.length < roots1.length)
				roots1 = roots2;
			else if(roots2.length > roots1.length)
				return newRoot(roots1,roots2);
			Thread.sleep(10);
		}
		return null;
	}
	
	
	
	private File newRoot(File[] roots1, File[] roots2) throws Exception
	{
		for(File f:roots2)
		if(!contains(roots1,f)) return f;
		throw new Exception("Could not find the new root (roots1="+roots1.length+" roots2="+roots2.length+")");
	}
	
	private boolean contains(File[] roots1, File f)
	{
		for(File f0:roots1) if(f0.equals(f)) return true;
		return false;
	}
}
