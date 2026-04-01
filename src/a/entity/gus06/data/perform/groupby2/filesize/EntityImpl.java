package a.entity.gus06.data.perform.groupby2.filesize;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180310";}


	private Service perform;
	private Service dirToFiles;
	private Service findList;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.list.groupby2.filesize");
		dirToFiles = Outside.service(this,"gus06.dir.listing.dirtofiles");
		findList = Outside.service(this,"gus06.find.list");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof List)		return perform.t(obj);
		if(obj instanceof Set)		return perform.t(findList.t(obj));
		if(obj instanceof File[])	return perform.t(findList.t(obj));
		if(obj instanceof File)		return perform.t(dirToFiles.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
