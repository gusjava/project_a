package a.entity.gus06.convert.stringtoproperties;

import a.framework.*;
import java.util.Properties;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201126";}

	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		byte[] b = s.getBytes();
		InputStream is = new ByteArrayInputStream(b);
		
		Properties prop = new Properties();
		
		try
		{prop.load(is);}
		finally{is.close();}
		return prop;
	}
}