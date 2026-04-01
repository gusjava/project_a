package a.entity.gus06.dir.listing.dirtomap.doubloon.size;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150409";}


	private Service size_files;
	private Service keepMulti;


	public EntityImpl() throws Exception
	{
		size_files = Outside.service(this,"gus06.dir.listing.dirtomap.size_files");
		keepMulti = Outside.service(this,"gus06.map.setmap.keepmulti");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		Map map = (Map) size_files.t(dir);
		keepMulti.p(map);
		return map;
	}
}
