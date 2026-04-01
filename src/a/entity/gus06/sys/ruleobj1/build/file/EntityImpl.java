package a.entity.gus06.sys.ruleobj1.build.file;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}

	
	public Object t(Object obj) throws Exception
	{
		return new File((String) obj);
	}
}
