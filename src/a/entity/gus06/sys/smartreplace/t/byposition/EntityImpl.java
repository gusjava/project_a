package a.entity.gus06.sys.smartreplace.t.byposition;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160430";}

	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.sys.quickreplace.t.byposition");}
	
	public Object t(Object obj) throws Exception
	{return perform.t(obj);}
}