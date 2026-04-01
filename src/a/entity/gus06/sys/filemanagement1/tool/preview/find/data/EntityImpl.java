package a.entity.gus06.sys.filemanagement1.tool.preview.find.data;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20200418";}
	
	
	private Service loadData;
	private Service findFile;

	public EntityImpl() throws Exception
	{
		loadData = Outside.service(this,"gus06.file.read.raw");
		findFile = Outside.service(this,"gus06.sys.filemanagement1.tool.preview.find.file");
	}
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) findFile.t(obj);
		if(file==null || !file.exists()) return null;
		return loadData.t(file);
	}
}
