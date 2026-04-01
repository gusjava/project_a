package a.entity.gus06.file.size.formatter1.fr;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170102";}


	private Service format;

	public EntityImpl() throws Exception
	{
		format = Outside.service(this,"gus06.string.transform.format.datasize.fr");
	}
	

	public Object t(Object obj) throws Exception
	{
		Long size = toLong(obj);
		return format.t(size);
	}
	
	private Long toLong(Object obj) throws Exception
	{
		if(obj instanceof Long) return (Long) obj;
		if(obj instanceof Integer) return Long.valueOf(((Integer) obj).longValue());
		if(obj instanceof String) return Long.valueOf((String) obj);
		if(obj instanceof File) return Long.valueOf(((File) obj).length());
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
