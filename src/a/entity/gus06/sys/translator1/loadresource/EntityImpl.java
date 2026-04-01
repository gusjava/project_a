package a.entity.gus06.sys.translator1.loadresource;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160304";}


	private Service tToR;
	private Service mapToR;
	private Service readProp;


	public EntityImpl() throws Exception
	{
		tToR = Outside.service(this,"gus06.convert.ttor");
		mapToR = Outside.service(this,"gus06.convert.maptor");
		readProp = Outside.service(this,"gus06.file.read.properties");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof R) return (R) obj;
		if(obj instanceof T) return (R) tToR.t(obj);
		if(obj instanceof Map) return (R) mapToR.t(obj);
		if(obj instanceof File) return (R) mapToR.t(readProp.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
