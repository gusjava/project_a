package a.entity.gus06.java.packagedir.hasfiles;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170923";}


	private Service listing;

	public EntityImpl() throws Exception
	{
		listing = Outside.service(this,"gus06.dir.listing0.ext.java");
	}
	
	public boolean f(Object obj) throws Exception
	{
		File dir = (File) obj;
		if(dir==null) return false;
		if(!dir.isDirectory()) return false;
		
		File[] ff = (File[]) listing.t(dir);
		return ff!=null && ff.length>0;
	}
}
