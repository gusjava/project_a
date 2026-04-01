package a.entity.gus06.find.reader;

import a.framework.*;
import java.io.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140818";}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Reader) return obj;
		if(obj instanceof File) return new FileReader((File) obj);
		if(obj instanceof String) return new StringReader((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
