package a.entity.gus06.sys.syndir1.synchronize1;

import a.framework.*;
import java.io.File;
import java.util.Iterator;
import java.io.PrintStream;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160808";}


	private Service buildPathMap;
	private Service replacer;
	private Service deleter;
	
	private PrintStream out;


	public EntityImpl() throws Exception
	{
		buildPathMap = Outside.service(this,"gus06.dir.listing.dirtopathmap");
		replacer = Outside.service(this,"gus06.file.op.copy.replace.syncmd5");
		deleter = Outside.service(this,"gus.x.file.op.delete");
		
		out = (PrintStream) Outside.resource(this,"sysout");
	}
	
	
	public void p(Object obj) throws Exception
	{
		File[] o = (File[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir1 = o[0];
		File dir2 = o[1];
		
		Map paths1 = (Map) buildPathMap.t(dir1);
		Map paths2 = (Map) buildPathMap.t(dir2);
		
		out.println("Initial nb: "+paths2.size());
		out.println("Final nb: "+paths1.size());
		
		performDelete(paths1,paths2);
		performReplace(paths1,dir2);
	}
	
	
	
	
	
	private void performDelete(Map paths1, Map paths2) throws Exception
	{
		int nbDeleted = 0;
		
		Iterator it = paths2.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f2 = (File) paths2.get(path);
			
			if(!paths1.containsKey(path))
			{
				deleter.p(f2);
				nbDeleted++;
			}
		}
		out.println("Deleted nb: "+nbDeleted);
	}
	
	
	
	
	private void performReplace(Map paths1, File dir2) throws Exception
	{
		int nbReplaced = 0;
		
		Iterator it = paths1.keySet().iterator();
		while(it.hasNext())
		{
			String path = (String) it.next();
			File f1 = (File) paths1.get(path);
			File f2 = new File(dir2,path);
			
			boolean done = replacer.f(new File[]{f1,f2});
			if(done) nbReplaced++;
		}
		out.println("Replaced nb: "+nbReplaced);
	}
}
