package a.entity.gus06.dir.runtask.fromclipboard.mergedirs1;

import a.framework.*;
import java.io.File;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20220410";}


	private Service fromClipboard;
	private Service buildListing;
	private Service relPathMap;
	private Service move;
	
	public EntityImpl() throws Exception
	{
		fromClipboard = Outside.service(this,"gus.y.clipboard1.files");
		buildListing = Outside.service(this,"gus06.dir.listing.dir1tofiles");
		relPathMap = Outside.service(this,"gus06.dir.listing.dirtomap.relpath_file");
		move = Outside.service(this,"gus06.file.op.move.autorename");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		Object progress = o[1];
		Set interrupt = (Set) o[2];
		
		if(progress!=null) ((V)progress).v("size","1");
		mergeDirs(dir);
		if(progress!=null) ((E)progress).e();
	}
	
	
	
	private void mergeDirs(File dir) throws Exception
	{
		List dirList = (List) fromClipboard.g();
		for(int i=0;i<dirList.size();i++)
		{
			File d = (File) dirList.get(i);
			if(d.isDirectory())
			{
				Map m = (Map) relPathMap.t(d);
				Iterator it = m.keySet().iterator();
				while(it.hasNext())
				{
					String relPath = (String) it.next();
					File f0 = (File) m.get(relPath);
					if(f0.isFile())
					{
						File f1 = new File(dir, relPath);
						move.p(new File[]{f0, f1});
					}
				}
			}
		}
	}
}