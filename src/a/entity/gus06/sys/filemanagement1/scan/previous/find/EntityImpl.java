package a.entity.gus06.sys.filemanagement1.scan.previous.find;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191128";}


	private Service listing;


	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.txt");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		File[] ff = (File[]) listing.t(dir);
		
		if(ff==null || ff.length==0) return null;
		if(ff.length==1) return ff[0];
		
		File f1 = ff[0];
		String name1 = f1.getName();
		
		for(File f : ff)
		{
			String name = f.getName();
			if(name.compareTo(name1)>0)
			{
				name1 = name;
				f1 = f;
			}
		}
		return f1;
	}
}
