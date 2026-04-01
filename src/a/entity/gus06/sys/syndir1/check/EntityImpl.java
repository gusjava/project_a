package a.entity.gus06.sys.syndir1.check;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.io.PrintStream;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170527";}


	private Service buildPathMap;
	private Service checkSame;


	public EntityImpl() throws Exception
	{
		buildPathMap = Outside.service(this,"gus06.dir.listing.dirtopathmap");
		checkSame = Outside.service(this,"gus06.file.doubloon.check");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir1 = o[0];
		File dir2 = o[1];
		
		Map paths1 = (Map) buildPathMap.t(dir1);
		Map paths2 = (Map) buildPathMap.t(dir2);
		
		Set deleted = new HashSet();
		Set added = new HashSet();
		Set replaced = new HashSet();
		Set unchanged = new HashSet();
		
		handleDelete(deleted,paths1,paths2);
		handleReplace(added,replaced,unchanged,paths1,dir2);
		
		Map m = new HashMap();
		
		m.put("paths1",paths1);
		m.put("paths2",paths2);
		
		m.put("deleted",deleted);
		m.put("added",added);
		m.put("replaced",replaced);
		m.put("unchanged",unchanged);
		
		return m;
	}
	
	
	
	
	
	private void handleDelete(Set deleted, Map paths1, Map paths2) throws Exception
	{
		Iterator it = paths2.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			if(!paths1.containsKey(path))
			deleted.add(path);
		}
	}
	
	
	
	
	private void handleReplace(Set added, Set replaced, Set unchanged, Map paths1, File dir2) throws Exception
	{
		Iterator it = paths1.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f1 = (File) paths1.get(path);
			File f2 = new File(dir2,path);
			
			if(!f2.isFile())
				added.add(path);
			else if(!checkSame.f(new File[]{f1,f2}))
				replaced.add(path);
			else unchanged.add(path);
		}
	}
}
