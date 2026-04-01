package a.entity.gus06.dir.runtask.fromclipboard.copy;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150605";}


	private Service fromClipboard;
	private Service fileCopy;
	private Service listing;
	
	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus06.clipboard.access.listfiles");
		fileCopy = Outside.service(this,"gus06.file.op.copy.replace");
		listing = Outside.service(this,"gus06.dir.listing.dirtopaths");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		List l1 = (List) fromClipboard.g();
		if(l1==null || l1.isEmpty()) return;
		
		List l2 = buildCopyList(l1,dir);
		
		if(progress!=null) ((V)progress).v("size",""+l2.size());
		for(int i=0;i<l2.size();i++)
		{
			File[] f = (File[]) l2.get(i);
			if(f[0].isDirectory()) f[1].mkdirs();
			else fileCopy.p(f);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	
	
	private List buildCopyList(List l1, File dir) throws Exception
	{
		List l2 = new ArrayList();
		for(int i=0;i<l1.size();i++)
		{
			File f = (File) l1.get(i);
			if(f.isFile()) addFileToList(l2,f,dir);
			else addDirToList(l2,f,dir);
		}
		return l2;
	}
	
	
	private void addFileToList(List l2, File f, File dir) throws Exception
	{
		File f1 = new File(dir,f.getName());
		l2.add(new File[]{f,f1});
	}
	
	
	private void addDirToList(List l2, File f, File dir) throws Exception
	{
		int length = f.getParentFile().getAbsolutePath().length();
		List pp = (List) listing.t(f);
		pp.add(f);
		
		for(Object p:pp)
		{
			File q0 = (File) p;
			String part = q0.getAbsolutePath().substring(length);
			File q1 = new File(dir,part);
			l2.add(new File[]{q0,q1});
		}
	}
}
