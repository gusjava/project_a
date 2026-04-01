package a.entity.gus06.convert.inputstreamtoproperties;

import a.framework.*;
import java.util.Properties;
import java.io.InputStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201126";}


	public Object t(Object obj) throws Exception
	{
		InputStream is = (InputStream) obj;
		Properties prop = new Properties();
		
		try
		{prop.load(is);}
		finally
		{if(is!=null) is.close();}
		return prop;
	}
}
