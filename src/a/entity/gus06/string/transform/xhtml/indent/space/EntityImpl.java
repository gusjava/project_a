package a.entity.gus06.string.transform.xhtml.indent.space;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220421";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.string.transform.xhtml.indent");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) perform.t(obj);
		return s.replace("\t"," ");
	}
}