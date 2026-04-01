package a.entity.gus06.convert.filetomap;

import a.framework.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260113";}

	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.file.read.properties.generic");
	}

	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		return perform.t(obj);
	}
}
