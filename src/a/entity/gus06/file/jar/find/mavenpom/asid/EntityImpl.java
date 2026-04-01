package a.entity.gus06.file.jar.find.mavenpom.asid;

import a.framework.*;
import java.util.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251230";}

	private Service readPom;

	public EntityImpl() throws Exception
	{
		readPom = Outside.service(this,"gus06.file.jar.find.mavenpom");
	}

	public Object t(Object obj) throws Exception
	{
		File file = (File) obj;
		Map pom = (Map) readPom.t(file);
		if(pom==null) return null;
		return pom.get("id");
	}
}
