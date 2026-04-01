package a.entity.gus06.sys.expression1.apply.op._app_mainclass;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180129";}

	public static final String T = "constant";
	

	private Service findClass;
		
	public EntityImpl() throws Exception
	{
		findClass = Outside.service(this,"gus06.app.jarfile.mainclass.class1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return findClass.g();
	}
}
