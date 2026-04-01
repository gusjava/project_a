package a.entity.gus06.sys.expression1.apply.op._video_prop;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201003";}


	private Service readProp;
	
	public EntityImpl() throws Exception
	{
		readProp = Outside.service(this,"gus06.file.video.generic.infomap");
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
			return file.isFile()?readProp.t(file):null;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
