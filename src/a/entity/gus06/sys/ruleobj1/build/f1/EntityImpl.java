package a.entity.gus06.sys.ruleobj1.build.f1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170117";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.filter.string.build.rule1");
	}
	
	public Object t(Object obj) throws Exception
	{
		return perform.t(obj);
	}
}
