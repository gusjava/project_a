package a.entity.gus06.dir.listing.dirtofiles.forext.jar;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170223";}
	
	public static final String EXT = "jar";

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
