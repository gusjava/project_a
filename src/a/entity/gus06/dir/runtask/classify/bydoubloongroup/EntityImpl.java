package a.entity.gus06.dir.runtask.classify.bydoubloongroup;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.HashSet;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150702";}


	private Service size_files;
	private Service buildMd5;
	private Service moveFile;
	
	public EntityImpl() throws Exception
	{
		size_files = Outside.service(this,"gus06.dir.listing.dirtomap.size_files");
		buildMd5 = Outside.service(this,"gus06.crypto.hash.md5.hexa");
		moveFile = Outside.service(this,"gus06.file.op.move.autorename");
	}

	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		Map map1 = (Map) size_files.t(dir);
		List keys = new ArrayList(map1.keySet());
		
		if(progress!=null) ((V)progress).v("size",""+keys.size());
		
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Set set = (Set) map1.get(key);
			
			if(set.size()>1) handleSet(set,dir);
			else moveOther((File) set.iterator().next(),dir);
			
			if(progress!=null) ((E)progress).e();
			if(interrupt!=null && !interrupt.isEmpty()) break;
		}
	}
	
	
	
	
	
	private void handleSet(Set set, File dir) throws Exception
	{
		Map m = new HashMap();
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			String md5 = (String) buildMd5.t(f);
			findSet(m,md5).add(f);
		}
		
		it = m.keySet().iterator();
		while(it.hasNext())
		{
			String md5 = (String) it.next();
			Set s = (Set) m.get(md5);
			if(s.size()>1) moveMd5Set(dir,md5,s);
			else moveOther((File) s.iterator().next(),dir);
		}
	}
	
	
	
	
	
	private void moveOther(File f, File dir) throws Exception
	{
		File d = new File(dir,"UNIQUE");
		File f1 = new File(d,f.getName());
		moveFile.p(new File[]{f,f1});
	}
	
	
	
	
	private Set findSet(Map map, String key)
	{
		if(!map.containsKey(key))
			map.put(key,new HashSet());
		return (Set) map.get(key);
	}
	
	
	
	
	private void moveMd5Set(File dir, String md5, Set set) throws Exception
	{
		File d = new File(dir,md5);
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			File f = (File) it.next();
			File f1 = new File(d,f.getName());
			moveFile.p(new File[]{f,f1});
		}
	}
}
