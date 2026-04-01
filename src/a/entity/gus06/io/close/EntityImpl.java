package a.entity.gus06.io.close;

import java.io.Closeable;
import java.io.IOException;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140706";}
	
	
	public void p(Object obj) throws Exception
	{
		try
		{
			if(obj!=null && obj instanceof Closeable)
				((Closeable) obj).close();
		}
		catch(IOException e){}
	}
}
