package a.entity.gus06.data.perform.keeplines;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190315";}


	private Service performString;
	private Service performFile;
	
	
	
	
	public EntityImpl() throws Exception
	{
		performString = Outside.service(this,"gus06.string.perform.keeplines");
		performFile = Outside.service(this,"gus06.file.string.perform.lines.keep.f");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		
		if(data instanceof String) return performString.t(obj);
		if(data instanceof File) return performFile.t(obj);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}
