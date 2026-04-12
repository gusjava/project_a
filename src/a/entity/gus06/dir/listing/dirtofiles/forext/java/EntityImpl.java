package a.entity.gus06.dir.listing.dirtofiles.forext.java;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170220";}
	
	public static final String EXT = "java";

	private Service find;

	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.dir.listing.dirtofiles.forext");
	}
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		return find.t(new Object[]{dir,EXT});
	}
}
