package a.entity.gus06.convert.stringtofile;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150626";}

	
	public Object t(Object obj) throws Exception
	{
		return new File((String) obj);
	}
}
