package a.entity.gus06.file.read.icon.from.ico;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191217";}

	private Service t;

	public EntityImpl() throws Exception
	{
		t = Outside.service(this,"gus06.file.read.ico.asicon");
	}
	
	public Object t(Object obj) throws Exception
	{
		return t.t(obj);
	}
}
