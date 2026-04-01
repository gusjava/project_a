package a.entity.gus06.string.transform.case1.upper.snakecase;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20220518";}

	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.string.case1.to.upper.snakecase");}

	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}