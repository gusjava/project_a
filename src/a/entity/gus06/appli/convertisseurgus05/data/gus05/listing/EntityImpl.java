package a.entity.gus06.appli.convertisseurgus05.data.gus05.listing;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150525";}
	
	
	private Service dirToListing;
	private Service getDir;
	
	
	public EntityImpl() throws Exception
	{
		dirToListing = Outside.service(this,"gus06.entitydev.gus05.listing");
		getDir = Outside.service(this,"gus06.appli.convertisseurgus05.option.dirgus05");
	}
	
	public Object g() throws Exception
	{
		File dir = (File) getDir.g();
		if(dir==null) return null;
		return dirToListing.t(dir);
	}
}
