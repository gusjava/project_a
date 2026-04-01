package a.entity.gus06.dir.listing0;

import java.io.File;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150618";}


	public Object t(Object obj) throws Exception
	{
		File dir = (File) obj;
		return dir.listFiles();
	}
}
