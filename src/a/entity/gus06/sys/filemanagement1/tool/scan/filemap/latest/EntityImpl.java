package a.entity.gus06.sys.filemanagement1.tool.scan.filemap.latest;

import a.framework.*;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191209";}


	private Service dirListing;
	private Service findPrevious;

	public EntityImpl() throws Exception
	{
		dirListing = Outside.service(this,"gus06.dir.listing0.dirs");
		findPrevious = Outside.service(this,"gus06.sys.filemanagement1.scan.previous.find");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir0 = (File) ((R) obj).r("dirScans");
		File[] dd = (File[]) dirListing.t(dir0);
		
		Map map = new HashMap();
		if(dd!=null) for(File d : dd)
		{
			String name = d.getName();
			File file = (File) findPrevious.t(d);
			if(file!=null) map.put(name,file);
		}
		return map;
	}
}