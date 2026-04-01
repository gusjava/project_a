package a.entity.gus06.string.transform.textlang.detect;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160629";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.sys.langdetect1.engine");
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(obj);
	}
}
