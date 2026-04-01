package a.entity.gus06.sys.expression1.apply.op._app_buildid1;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170411";}

	public static final String T = "constant";
	

	private Service find1;
	private Service find2;
		
	public EntityImpl() throws Exception
	{
		find1 = Outside.service(this,"gus06.app.info.buildid");
		find2 = Outside.service(this,"gus06.app.info.buildtime");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String id = (String) find1.g();
		String time = (String) find2.g();
		return id+"|"+time;
	}
}
