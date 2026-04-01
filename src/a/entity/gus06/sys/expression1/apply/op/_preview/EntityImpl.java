package a.entity.gus06.sys.expression1.apply.op._preview;

import a.framework.*;
import java.io.File;
import java.net.URL;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160529";}


	private Service readImage;
	
	public EntityImpl() throws Exception
	{
		readImage = Outside.service(this,"gus06.file.read.image.preview");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof File)
		{
			File file = (File) obj;
			return file.isFile()?readImage.t(file):null;
		}
		if(obj instanceof URL)
		{
			throw new Exception("Not supported yet: "+obj.getClass().getName());
		}
		if(obj instanceof byte[])
		{
			throw new Exception("Not supported yet: "+obj.getClass().getName());
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
