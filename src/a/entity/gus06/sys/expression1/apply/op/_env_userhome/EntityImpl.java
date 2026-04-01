package a.entity.gus06.sys.expression1.apply.op._env_userhome;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180424";}

	public static final String T = "constant";


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.system.prop.userhome");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return find.g();
	}
}
