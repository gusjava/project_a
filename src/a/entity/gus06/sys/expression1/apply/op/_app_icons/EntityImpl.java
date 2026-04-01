package a.entity.gus06.sys.expression1.apply.op._app_icons;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.jarfile.listing.resources.iconmap.gyem");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return perform.g();
	}
}
