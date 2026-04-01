package a.entity.gus06.data.perform.inferfile;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180320";}


	private Service performSplit;
	private Service filterChildren;
	
	public EntityImpl() throws Exception
	{
		performSplit = Outside.service(this,"gus06.data.perform.split");
		filterChildren = Outside.service(this,"gus06.dir.listing0.mstars");
	}



	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String s = (String) o[0];
		File dir = (File) o[1];
		
		String s1 = s.replace("*","B");
		File f1 = new File(s1);
		
		if(f1.isAbsolute()) return infer(s);
		return infer(dir,s);
		
	}
	
	
	
	private File infer(String s) throws Exception
	{
		Set set = new HashSet();
		
		String[] nn = (String[]) performSplit.t(new Object[]{s,File.separator});
		for(String n : nn) set = rebuildSet(set,n);
		
		if(set.isEmpty()) return null;
		return (File) set.iterator().next();
	}
	
	
	
	private File infer(File dir, String s) throws Exception
	{
		Set set = new HashSet();
		set.add(dir);
		
		String[] nn = (String[]) performSplit.t(new Object[]{s,File.separator});
		for(String n : nn) set = rebuildSet(set,n);
		
		if(set.isEmpty()) return null;
		return (File) set.iterator().next();
	}
	
	
	private Set rebuildSet(Set set, String n) throws Exception
	{
		Set set1 = new HashSet();
		if(set.isEmpty()) initSet(set);
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			File d = (File) it.next();
			if(d.isDirectory())
			{
				File[] ff = (File[]) filterChildren.t(new Object[]{d,n});
				if(ff!=null) for(File f : ff) set1.add(f);
			}
		}
		return set1;
	}
	
	
	
	private void initSet(Set set)
	{
		File[] roots = File.listRoots();
		for(File root : roots) set.add(root);
	}
}
