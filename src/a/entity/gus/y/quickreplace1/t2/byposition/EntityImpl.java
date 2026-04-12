package a.entity.gus.y.quickreplace1.t2.byposition;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240714";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus.y.quickreplace1.t1.byposition");}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}