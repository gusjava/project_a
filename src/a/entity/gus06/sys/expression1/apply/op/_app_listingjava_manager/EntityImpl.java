package a.entity.gus06.sys.expression1.apply.op._app_listingjava_manager;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180129";}

	public static final String T = "constant";
	

	private Service find;
		
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.app.jarfile.listing.java.manager");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return find.g();
	}
}
