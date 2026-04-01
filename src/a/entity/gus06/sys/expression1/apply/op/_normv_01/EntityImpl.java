package a.entity.gus06.sys.expression1.apply.op._normv_01;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191022";}


	private Service normTabDouble;
	private Service normTabInt;
	
	public EntityImpl() throws Exception
	{
		normTabDouble = Outside.service(this,"gus06.math.tabdouble.normalize.norm.n01");
		normTabInt = Outside.service(this,"gus06.math.tabint.normalize.norm.n01");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof double[]) return normTabDouble.t(obj);
		if(obj instanceof int[]) return normTabInt.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
