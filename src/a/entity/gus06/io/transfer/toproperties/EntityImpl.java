package a.entity.gus06.io.transfer.toproperties;

import a.framework.*;
import java.io.*;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231125";}
	
	
	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		Properties p = new Properties();
		p.load(is);
		is.close();
		return p;
	}
}
