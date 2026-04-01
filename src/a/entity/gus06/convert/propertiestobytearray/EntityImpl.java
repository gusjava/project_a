package a.entity.gus06.convert.propertiestobytearray;

import a.framework.*;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161015";}

	
	
	public Object t(Object obj) throws Exception
	{
		Properties prop = (Properties) obj;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		prop.store(baos,"");
		byte[] b = baos.toByteArray();
		baos.close();
		
		return b;
	}
}
