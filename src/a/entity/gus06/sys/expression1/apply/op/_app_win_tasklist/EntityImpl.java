package a.entity.gus06.sys.expression1.apply.op._app_win_tasklist;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190522";}

	public static final String T = "constant";


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.app.win.tasklist");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return perform.g();
	}
}
