package a.entity.gus06.dir.hdd.space.total.formatted.en;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140910";}


	private Service formatter;


	public EntityImpl() throws Exception
	{
		formatter = Outside.service(this,"gus06.file.size.formatter1.en");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		long size = file.getTotalSpace();
		return formatSize(Long.valueOf(size));
	}
	
	
	private String formatSize(Long size) throws Exception
	{return (String) formatter.t(size);}
}
