package a.entity.gus06.sys.filemanagement1.scan.timestamps.find;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191226";}


	private Service listing;


	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] ff = (File[]) listing.t(dir);
		
		List list = new ArrayList();
		if(ff==null || ff.length==0) return list;
		
		for(File f : ff)
		{
			String name = f.getName();
			list.add(name.substring(0,15));
		}
		Collections.sort(list,Collections.reverseOrder());
		return list;
	}
}
