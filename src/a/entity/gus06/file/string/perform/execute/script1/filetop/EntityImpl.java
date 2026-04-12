package a.entity.gus06.file.string.perform.execute.script1.filetop;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260130";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.script1.build1.p");
	}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}